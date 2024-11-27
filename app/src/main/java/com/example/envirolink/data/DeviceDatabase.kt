package com.example.envirolink.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Device::class], version = 1, exportSchema = false)
abstract class DeviceDatabase: RoomDatabase() {
    abstract fun deviceDao(): DeviceDao

    companion object {
        @Volatile
        private var INSTANCE: DeviceDatabase? = null

        fun getDatabase(context: Context): DeviceDatabase {
            return INSTANCE ?: synchronized(this) {
                // Log when database is being created or accessed
                Log.d("DeviceDatabase", "Creating new database instance.")
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeviceDatabase::class.java,
                    "device_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}