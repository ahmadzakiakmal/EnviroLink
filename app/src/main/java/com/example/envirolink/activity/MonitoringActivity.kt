package com.example.envirolink.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.device.DeviceScreenDetail
import com.example.envirolink.ui.pages.MonitoringScreen

class MonitoringActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deviceName = intent.getStringExtra("deviceName") ?: "Unknown Device"
        val deviceId = intent.getStringExtra("deviceId") ?: "Unknown ID"

        setContent {
            DeviceScreenDetail(
                deviceName =deviceName,
                deviceId  = deviceId,
                onBack = { finish() }
            )
            BottomNavBar(context = this)
        }
    }
}
