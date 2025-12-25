package com.example.climasphere2.network

data class ForecastResponse(
    val list: List<ForecastListItem>
)

data class ForecastListItem(
    val dt: Long,
    val main: ForecastMain,
    val weather: List<Weather>,
    val wind: Wind,
    val pop: Double? // <--- Add this line
)

data class ForecastMain(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val humidity: Int
)
