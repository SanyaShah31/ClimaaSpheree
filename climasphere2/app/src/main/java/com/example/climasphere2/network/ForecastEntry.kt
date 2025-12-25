package com.example.climasphere2.models

data class ForecastEntry(
    val dt: Long,
    val main: MainData,
    val weather: List<WeatherData>,
    val wind: WindData,
    val visibility: Int
)

data class MainData(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val humidity: Int
)

data class WeatherData(
    val description: String,
    val icon: String
)

data class WindData(
    val speed: Double
)
