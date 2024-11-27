package com.example.envirolink.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.envirolink.model.WeatherResponse
import com.example.envirolink.network.WeatherRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    fun fetchWeatherData() {
        viewModelScope.launch {
            try {
                val response = WeatherRetrofitInstance.api.getWeatherData(
                    apiKey = WeatherRetrofitInstance.getApiKey(),
                    location = "Kab Sleman",
                    includeAQI = "yes"
                )
                _weatherData.value = response
            } catch (e: Exception) {
                // Handle errors
                e.printStackTrace()
            }
        }
    }
}
