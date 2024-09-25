package com.example.envirolink.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.pages.WeatherDetailScreen
import kotlin.random.Random

class WeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temperature = Random.nextInt(29, 40)
        setContent {
            WeatherDetailScreen(temperature)
            BottomNavBar(context = this)
        }
    }

    override fun onResume() {
        super.onResume()
        val temperature = Random.nextInt(29, 40)
        Log.d("Lifecycle", "Resume Activity, new temperature value: $temperature")
        setContent {
            WeatherDetailScreen(temperature)
            BottomNavBar(context = this)
        }
    }
}