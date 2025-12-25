package com.example.climaaspheree

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.net.HttpURLConnection
import java.net.URL

class WeatherUpdateWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val city = "London" // change if needed
        val apiKey = "cbbfc9fa7efe023e38d43e61ab046562"
        val urlString = "https://api.openweathermap.org/data/2.5/forecast?q=$city&units=metric&appid=$apiKey"

        return try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 10000
            connection.readTimeout = 10000

            val responseCode = connection.responseCode
            Log.d("WeatherWorker", "HTTP Response Code: $responseCode")

            if (responseCode == 200) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                Log.d("WeatherWorker", "Full API Response: $response")
                Result.success()
            } else {
                val errorResponse = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Log.e("WeatherWorker", "Error Response: $errorResponse")
                Result.failure()
            }
        } catch (e: Exception) {
            Log.e("WeatherWorker", "Exception while fetching forecast", e)
            Result.retry()
        }
    }
}
