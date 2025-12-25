package com.example.climaaspheree.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForecastRepository(private val api: WeatherApiService, private val apiKey: String) {

    suspend fun getSevenDayForecast(lat: Double, lon: Double): ForecastResponse {
        return withContext(Dispatchers.IO) {
            api.getFiveDayForecast(lat, lon, apiKey = apiKey)
        }
    }

    suspend fun getCurrentWeather(city: String): CurrentWeatherResponse {
        return withContext(Dispatchers.IO) {
            api.getCurrentWeather(city, apiKey)
        }
    }
}
