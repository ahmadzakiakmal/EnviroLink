package com.example.envirolink.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.pages.HomeScreen
import com.example.envirolink.viewmodel.ForecastViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeActivity : ComponentActivity() {
    private val forecastViewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forecastViewModel.fetchForecastData()
        lifecycleScope.launch {
            forecastViewModel.forecastData.collectLatest { weather ->
                weather?.let {
                    setContent {
                        HomeScreen(current = it.current, forecast = it.forecast)
                        BottomNavBar(context = this@HomeActivity)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        forecastViewModel.fetchForecastData()
        lifecycleScope.launch {
            forecastViewModel.forecastData.collectLatest { weather ->
                weather?.let {
                    setContent {
                        HomeScreen(current = it.current, forecast = it.forecast)
                        BottomNavBar(context = this@HomeActivity)
                    }
                }
            }
        }
    }
}
