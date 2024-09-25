package com.example.envirolink.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val API_KEY = "dd03d905fbdf4195bf6b2b7a57f790aa" // Replace with your actual API key

    // Creating an interceptor to add the API key to every request
    private val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        val originalUrl = original.url

        // Add the API key as a query parameter to every request
        val url = originalUrl.newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()

        // Modify the request to include the new URL
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    // Creating a logging interceptor to log request/response information for debugging
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Building the OkHttpClient with the interceptors
    private val client = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor) // Automatically adds API key
        .addInterceptor(loggingInterceptor) // Logs HTTP requests/responses
        .build()

    // Creating the Retrofit instance
    val api: NewsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // Add custom OkHttp client with interceptors
            .addConverterFactory(GsonConverterFactory.create()) // Converts JSON to Kotlin objects
            .build()
            .create(NewsApiService::class.java)
    }
}
