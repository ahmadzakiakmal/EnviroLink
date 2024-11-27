package com.example.envirolink.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val devicesRepository: DevicesRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [DevicesRepository]
     */
    override val devicesRepository: DevicesRepository by lazy {
        OfflineDevicesRepository(DeviceDatabase.getDatabase(context).deviceDao())
    }
}