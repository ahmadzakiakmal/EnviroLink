package com.example.envirolink.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.pages.WeatherTipsScreen
import kotlinx.coroutines.*

class WeatherTipsActivity : ComponentActivity() {
    private var weatherCondition = mutableStateOf("")
    private var weatherJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        generateRandomWeatherCondition()
        setContent {
            WeatherTipsScreen(condition = weatherCondition.value,)
            BottomNavBar(context = this)
        }
    }

    override fun onResume() {
        super.onResume()
        startWeatherUpdates()
    }

    override fun onPause() {
        super.onPause()
        stopWeatherUpdates()
    }

    private fun generateRandomWeatherCondition() {
        val conditions = listOf("Sunny", "Cloudy", "Rainy", "Stormy", "Windy", "Foggy")
        weatherCondition.value = conditions.random()
    }

    private fun startWeatherUpdates() {
        weatherJob = CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                generateRandomWeatherCondition()
                delay(5000)
            }
        }
    }

    private fun stopWeatherUpdates() {
        weatherJob?.cancel()
    }
}