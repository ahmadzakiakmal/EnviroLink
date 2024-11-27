package com.example.envirolink.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {
    @Insert
    suspend fun insert(device: Device)

    @Query("SELECT * FROM devices")
    fun getAllDevices(): Flow<List<Device>>
}
