package com.example.envirolink.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.envirolink.ui.pages.MonitoringScreen

class MonitoringActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deviceName = intent.getStringExtra("DEVICE_NAME") ?: "Unknown Device"
        val deviceId = intent.getStringExtra("DEVICE_ID") ?: "Unknown ID"

        setContent {
            MonitoringScreen(
                deviceName = deviceName,
//                deviceId = deviceId,
                onBack = { finish() }
            )
        }
    }
}
