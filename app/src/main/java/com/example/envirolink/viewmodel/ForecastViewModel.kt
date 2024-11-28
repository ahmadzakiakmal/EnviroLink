package com.example.envirolink.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.envirolink.model.ForecastResponse
import com.example.envirolink.network.WeatherRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {

    private val _forecastData = MutableStateFlow<ForecastResponse?>(null)
    val forecastData: StateFlow<ForecastResponse?> = _forecastData

    fun fetchForecastData() {
        viewModelScope.launch {
            try {
                val response = WeatherRetrofitInstance.api.getForecastData(
                    apiKey = WeatherRetrofitInstance.getApiKey(),
                    location = "Kab Sleman",
                    days = 3,
                    includeAQI = "yes",
                    includeAlerts = "no",
                    hour = 12
                )
                _forecastData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
