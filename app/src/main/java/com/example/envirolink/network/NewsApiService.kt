package com.example.envirolink.network

import com.example.envirolink.model.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun getWeatherNews(
        @Query("q") query: String,
        @Query("language") language: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("pageSize") pageSize: Int,
    ): ArticleResponse
}
