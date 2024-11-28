package com.example.envirolink.network

import com.example.envirolink.model.ForecastResponse
import com.example.envirolink.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    suspend fun getWeatherData(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("aqi") includeAQI: String
    ): WeatherResponse

    @GET("forecast.json")
    suspend fun getForecastData(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") includeAQI: String,
        @Query("alerts") includeAlerts: String,
        @Query("hour") hour: Int
    ): ForecastResponse
}