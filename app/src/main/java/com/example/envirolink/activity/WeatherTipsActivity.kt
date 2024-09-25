package com.example.envirolink.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.pages.WeatherTipsScreen

class WeatherTipsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val condition = mutableStateOf("")
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTipsScreen(condition)
            BottomNavBar(context = this)
        }
    }
}