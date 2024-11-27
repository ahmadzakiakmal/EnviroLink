package com.example.envirolink.ui.device

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.envirolink.data.Device
import com.example.envirolink.data.DevicesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DeviceUiState(
    val deviceDetails: DeviceDetails = DeviceDetails(),
    val isEntryValid: Boolean = false
)

data class DeviceDetails(
    val id: String = "",
    val name: String = "",
)

fun DeviceDetails.toItem(): Device = Device(
    id = id,
    name = name,
)
class DeviceViewModel(private val devicesRepository: DevicesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(DeviceUiState())
    val uiState: StateFlow<DeviceUiState> = _uiState.asStateFlow()

    private val _devices = MutableStateFlow<List<Device>>(emptyList())
    val devices: StateFlow<List<Device>> = _devices

    init {
        viewModelScope.launch {
            devicesRepository.getAllDevicesStream().collect { deviceList ->
                _devices.value = deviceList
            }
        }
    }

    fun updateDeviceDetails(id: String? = null, name: String? = null) {
        _uiState.value = _uiState.value.copy(
            deviceDetails = _uiState.value.deviceDetails.copy(
                id = id ?: _uiState.value.deviceDetails.id,
                name = name ?: _uiState.value.deviceDetails.name,
            ),
            isEntryValid = validateInput(
                id = id ?: _uiState.value.deviceDetails.id,
                name = name ?: _uiState.value.deviceDetails.name,
            )
        )
    }

    fun addDevice() {
        val device = _uiState.value.deviceDetails.toItem()
        viewModelScope.launch {
            try {
                devicesRepository.insertDevice(device)
                Log.d("DeviceViewModel", "Device added: $device")
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error adding device", e)
            }
        }
    }

    private fun validateInput(id: String, name: String): Boolean {
        return id.isNotBlank() && name.isNotBlank()
    }
}