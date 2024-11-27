package com.example.envirolink.data

import kotlinx.coroutines.flow.Flow

class OfflineDevicesRepository(private val deviceDao: DeviceDao) : DevicesRepository  {
    override suspend fun getAllDevicesStream(): Flow<List<Device>> = deviceDao.getAllDevices()

    override suspend fun insertDevice(device: Device) = deviceDao.insert(device)
}