package com.example.envirolink.data

import kotlinx.coroutines.flow.Flow

interface DevicesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    suspend fun getAllDevicesStream(): Flow<List<Device>>

    /**
     * Insert item in the data source
     */
    suspend fun insertDevice(device: Device)
}