package com.example.climaaspheree.api

import com.example.climaaspheree.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface
NewsApiService {
    @GET("v2/everything")
    fun getWeatherNews(
        @Query("q") query: String = "weather forecast OR storm OR rainfall OR temperature", // 🌦️ Only weather-related news
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}
