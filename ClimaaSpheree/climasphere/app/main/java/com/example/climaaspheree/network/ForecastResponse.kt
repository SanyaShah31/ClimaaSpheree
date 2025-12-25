package com.example.climaaspheree.network

data class ForecastResponse(
    val daily: List<ForecastItem>,
    val alerts: List<WeatherAlert>? = null
)

data class ForecastItem(
    val dt: Long,
    val temp: Temp,
    val weather: List<ForecastWeather>, // ✅ Renamed to ForecastWeather
    val wind_speed: Double,
    val humidity: Int
)

data class Temp(
    val min: Double,
    val max: Double
)

// ✅ New ForecastWeather class (no conflict with Weather)
data class ForecastWeather(
    val main: String,
    val description: String,
    val icon: String
)

data class WeatherAlert(
    val sender_name: String,
    val event: String,
    val start: Long,
    val end: Long,
    val description: String
)
