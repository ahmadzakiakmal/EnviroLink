    package com.example.envirolink.activity

    import android.Manifest
    import android.content.pm.PackageManager
    import android.graphics.Bitmap
    import android.os.Bundle
    import android.util.Log
    import android.util.Size
    import android.widget.Toast
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.result.contract.ActivityResultContracts
    import androidx.camera.core.CameraSelector
    import androidx.camera.core.ImageAnalysis
    import androidx.camera.core.Preview
    import androidx.camera.core.resolutionselector.ResolutionSelector
    import androidx.camera.core.resolutionselector.ResolutionStrategy
    import androidx.camera.lifecycle.ProcessCameraProvider
    import androidx.camera.view.PreviewView
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.viewinterop.AndroidView
    import androidx.core.content.ContextCompat
    import com.example.envirolink.components.BottomNavBar
    import com.example.envirolink.ui.device.DeviceScreen
    import com.google.zxing.BinaryBitmap
    import com.google.zxing.MultiFormatReader
    import com.google.zxing.NotFoundException
    import com.google.zxing.RGBLuminanceSource
    import com.google.zxing.common.HybridBinarizer
    import kotlinx.coroutines.CoroutineScope
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.launch
    import kotlinx.coroutines.withContext
    import android.os.Handler
    import android.os.Looper
    import androidx.compose.foundation.background
    import androidx.compose.material3.CircularProgressIndicator
    import androidx.compose.runtime.collectAsState
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.graphics.Color
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import com.example.envirolink.data.DeviceDatabase
    import com.example.envirolink.data.DevicesRepository
    import com.example.envirolink.data.OfflineDevicesRepository
    import com.example.envirolink.ui.device.DeviceViewModel

    class DeviceViewModelFactory(private val repository: DevicesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DeviceViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DeviceViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
    class DeviceActivity : ComponentActivity() {
        private lateinit var deviceViewModel: DeviceViewModel

        private var previewView: PreviewView? = null
        private var isCameraInitialized = false

        private val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                startCamera()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val deviceDao = DeviceDatabase.getDatabase(this).deviceDao()
            Log.d("DeviceActivity", "DeviceDao successfully initialized!")

            val repository = OfflineDevicesRepository(deviceDao)

            deviceViewModel = ViewModelProvider(
                this,
                DeviceViewModelFactory(repository)
            )[DeviceViewModel::class.java]

            setContent {
                val context = this
                val devices by deviceViewModel.devices.collectAsState()

                var showCamera by remember { mutableStateOf(false) }
                var showModal by remember { mutableStateOf(false) }

                var isLoading by remember { mutableStateOf(false) }
                var scannedQrCode by remember { mutableStateOf<String?>(null) }

                Box(modifier = Modifier.fillMaxSize()) {
                    DeviceScreen(
                        isLoggedIn = true,
                        devices = devices,
                        onLoginClick = { },
                        scanQrCode = {
                            when {
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                ) == PackageManager.PERMISSION_GRANTED -> {
                                    showModal = false
                                    showCamera = true

                                    startCamera { qrCodeText ->
                                        showCamera = false

                                        isLoading = true
                                        scannedQrCode = qrCodeText

                                        Handler(Looper.getMainLooper()).postDelayed({
                                            deviceViewModel.updateDeviceDetails(id=scannedQrCode)
                                            isLoading = false
                                            showModal = true
                                            Toast.makeText(context, "QR Code Scanned", Toast.LENGTH_SHORT).show()
                                        }, 1000)
                                    }
                                }
                                else -> {
                                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                                }
                            }
                        },
                        showModal = showModal,
                        onShowModalChange = { showModal = it },
                        viewModel = deviceViewModel
                    )

                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f)),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Color.White)
                        }
                    }
                    if (showCamera) {
                        CameraPreview()
                    }
                }

                BottomNavBar(context)
            }
        }

        private fun startCamera(onQrCodeScanned: ((String) -> Unit)? = null) {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()
                    .setResolutionSelector(
                        ResolutionSelector.Builder()
                            .setResolutionStrategy(
                                ResolutionStrategy(Size(1280, 720), ResolutionStrategy.FALLBACK_RULE_NONE)
                            )
                            .build()
                    )
                    .build()
                    .also {
                        it.setSurfaceProvider(previewView?.surfaceProvider)
                    }

                // Helper function to convert bitmap to int array
                fun convertBitmapToIntArray(bitmap: Bitmap): IntArray {
                    val width = bitmap.width
                    val height = bitmap.height
                    val pixels = IntArray(width * height)
                    bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
                    return pixels
                }

                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(ContextCompat.getMainExecutor(this)) { imageProxy ->
                            CoroutineScope(Dispatchers.Default).launch {
                                try {
                                    val bitmap = imageProxy.toBitmap()
                                    val source = BinaryBitmap(HybridBinarizer(RGBLuminanceSource(
                                        bitmap.width,
                                        bitmap.height,
                                        convertBitmapToIntArray(bitmap)
                                    )))

                                    try {
                                        val result = MultiFormatReader().decode(source)

                                        // Switch to Main dispatcher for UI updates
                                        withContext(Dispatchers.Main) {
                                            // Stop further scanning
                                            cameraProvider.unbindAll()

                                            // Invoke callback with scanned QR code text
                                            onQrCodeScanned?.invoke(result.text)
                                        }
                                    } catch (e: NotFoundException) {
                                        // No QR code found
                                        Log.d("QRScanner", "No QR code found")
                                    }
                                } catch (e: Exception) {
                                    Log.e("QRScanner", "Error processing image", e)
                                } finally {
                                    imageProxy.close()
                                }
                            }
                        }
                    }

                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        this,
                        cameraSelector,
                        preview,
                        imageAnalyzer
                    )
                    isCameraInitialized = true
                } catch (exc: Exception) {
                    Toast.makeText(this, "Camera setup failed: ${exc.message}", Toast.LENGTH_SHORT).show()
                }
            }, ContextCompat.getMainExecutor(this))
        }

        @Composable
        fun CameraPreview() {
            AndroidView(
                factory = { context ->
                    PreviewView(context).apply {
                        previewView = this
                        layoutParams = androidx.constraintlayout.widget.ConstraintLayout.LayoutParams(
                            androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.MATCH_PARENT,
                            androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.MATCH_PARENT
                        )
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }