package com.example.envirolink.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.pages.WeatherDetailScreen
import com.example.envirolink.viewmodel.WeatherViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

class WeatherActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel.fetchWeatherData()
        lifecycleScope.launch {
            weatherViewModel.weatherData.collectLatest { weather ->
                weather?.let {
//                    Log.d("WeatherActivity", "Fetched Weather Data: ${it.current.temp_c}")
                    setContent {
                        WeatherDetailScreen(
                            temperature = it.current.temp_c,
                            cloud = it.current.cloud,
                            wind_kph = it.current.wind_kph,
                            humidity = it.current.humidity,
                            uv = it.current.uv,
                            heatIndex = it.current.heatindex_c,
                            visibilityKm = it.current.vis_km,
                            aqi = it.current.air_quality.o3.toInt()
                        )
                        BottomNavBar(context = this@WeatherActivity)
                    }
                }
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//        val temperature = Random.nextInt(29, 40)
//        Log.d("Lifecycle", "Resume Activity, new temperature value: $temperature")
//        setContent {
//            WeatherDetailScreen(temperature)
//            BottomNavBar(context = this)
//        }
//    }
}