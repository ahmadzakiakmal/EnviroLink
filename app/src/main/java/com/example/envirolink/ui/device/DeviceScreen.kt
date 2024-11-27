package com.example.envirolink.ui.device

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Contactless
import androidx.compose.material.icons.outlined.QrCode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.envirolink.activity.HomeActivity
import com.example.envirolink.activity.MonitoringActivity
import com.example.envirolink.data.Device
import com.example.envirolink.ui.theme.InriaSansFamily

@Composable
fun DeviceScreen(
    isLoggedIn: Boolean,
    devices: List<Device>,
    onLoginClick: () -> Unit,
    scanQrCode: () -> Unit,
    showModal: Boolean,
    onShowModalChange: (Boolean) -> Unit,
    viewModel: DeviceViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        if (isLoggedIn) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Perangkat Tersambung",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        modifier = Modifier.weight(1f),
                        fontFamily = InriaSansFamily
                    )
                    IconButton(
                        onClick = { onShowModalChange(true) },
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                color = Color(0xFF539DF3),
                                shape = RoundedCornerShape(50)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Tambahkan Perangkat",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                if (devices.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Contactless,
                            contentDescription = "Tidak ada perangkat",
                            tint = Color.Gray,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Tidak terdapat perangkat tersambung",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            fontFamily = InriaSansFamily
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal=16.dp)
                    ) {
                        items(devices) { device ->
                            DeviceCard(device = device)
                        }
                    }
                }
            }
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.Center),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Mempunyai perangkat EnviroLink?",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { onLoginClick() },
                        modifier = Modifier.fillMaxWidth(0.6f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF539DF3),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Login")
                    }
                }
            }
        }

        if (showModal) {
            AddDeviceDialog(
                deviceId = uiState.deviceDetails.id,
                deviceName = uiState.deviceDetails.name,
                onDeviceIdChange = { viewModel.updateDeviceDetails(id = it) },
                onDeviceNameChange = { viewModel.updateDeviceDetails(name = it) },
                onConfirmDevice = {
                    if (uiState.isEntryValid) {
                        viewModel.addDevice()
                        onShowModalChange(false)
                    }
                },
                onDismissRequest = { onShowModalChange(false) },
                onCameraOpen = { scanQrCode() }
            )
        }
    }
}

@Composable
fun AddDeviceDialog(
    deviceId: String,
    deviceName: String,
    onDeviceIdChange: (String) -> Unit,
    onDeviceNameChange: (String) -> Unit,
    onConfirmDevice: () -> Unit,
    onDismissRequest: () -> Unit,
    onCameraOpen: () -> Unit,
    formFieldShape: RoundedCornerShape = RoundedCornerShape(12.dp)
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                "Tambahkan Perangkat",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color(0xFF333333),
                    fontSize = 20.sp
                )
            )
        },
        text =  {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Column {
                    Text(
                        "ID Perangkat",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFF666666),
                            fontSize = 12.sp
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextFieldWithLabel(
                            value = deviceId,
                            onValueChange = { onDeviceIdChange(it) },
                            modifier = Modifier
                                .weight(1f)
                                .clip(formFieldShape)
                                .background(Color(0xFFF0F0F0)),
                        )
                        IconButton(
                            onClick = { onCameraOpen() },
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .background(
                                    color = Color(0xFFF0F0F0),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.QrCode,
                                contentDescription = "Scan QR Code",
                                tint = Color(0xFF539DF3)
                            )
                        }
                    }
                }

                Column {
                    Text(
                        "Nama Perangkat",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFF666666),
                            fontSize = 12.sp
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    TextFieldWithLabel(
                        value = deviceName,
                        onValueChange = { onDeviceNameChange(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(formFieldShape)
                            .background(Color(0xFFF0F0F0)),
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onConfirmDevice,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFF539DF3)
                )
            ) {
                Text("Tambahkan", style = TextStyle(fontSize = 16.sp))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFF666666)
                )
            ) {
                Text("Batal", style = TextStyle(fontSize = 16.sp))
            }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(24.dp)
    )
}

@Composable
fun TextFieldWithLabel(
    value: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    formFieldShape: RoundedCornerShape = RoundedCornerShape(12.dp)
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF0F0F0),
            unfocusedContainerColor = Color(0xFFF0F0F0),
            disabledContainerColor = Color(0xFFF0F0F0),
            cursorColor = Color(0xFF539DF3),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = Color(0xFF333333),
            fontSize = 16.sp
        ),
        singleLine = true,
        shape = formFieldShape
    )
}

@Composable
fun DeviceCard(
    device: Device,
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                val intent = Intent(context, MonitoringActivity::class.java).apply {
                    putExtra("deviceName", device.name)
                    putExtra("deviceId", device.id)
                }
                context.startActivity(intent)
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Contactless,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = Color(0xFF539DF3)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = device.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
    }
}