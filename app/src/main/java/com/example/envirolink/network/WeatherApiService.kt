package com.example.envirolink.network

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
}