package com.example.envirolink.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "devices")
data class Device(
    @PrimaryKey val id: String,
    val name: String,
)