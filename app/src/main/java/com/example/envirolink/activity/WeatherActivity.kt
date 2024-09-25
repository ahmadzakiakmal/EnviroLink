package com.example.envirolink.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.pages.WeatherDetailScreen

class WeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherDetailScreen()
            BottomNavBar(context = this)
        }
    }
}