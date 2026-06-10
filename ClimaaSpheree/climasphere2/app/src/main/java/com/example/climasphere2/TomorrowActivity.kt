package com.example.climasphere2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.climasphere2.network.ForecastListItem
import com.example.climasphere2.network.RetrofitInstance
import com.example.climasphere2.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class TomorrowActivity : AppCompatActivity() {

    private lateinit var llForecastList: LinearLayout
    private lateinit var imgWeatherIcon: ImageView
    private lateinit var tvTomorrowDay: TextView
    private lateinit var tvTomorrowCondition: TextView
    private lateinit var tvTomorrowTemp: TextView
    private lateinit var tvRain: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvHumidity: TextView

    private val apiKey = "cbbfc9fa7efe023e38d43e61ab046562"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomorrow)

        llForecastList = findViewById(R.id.llForecastList)
        imgWeatherIcon = findViewById(R.id.imgWeatherIcon)
        tvTomorrowDay = findViewById(R.id.tvTomorrowDay)
        tvTomorrowCondition = findViewById(R.id.tvTomorrowCondition)
        tvTomorrowTemp = findViewById(R.id.tvTomorrowTemp)
        tvRain = findViewById(R.id.tvRainValue)
        tvWind = findViewById(R.id.tvWindValue)
        tvHumidity = findViewById(R.id.tvHumidityValue)

        val city = intent.getStringExtra("city_name") ?: "London"
        fetchForecast(city)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun fetchForecast(cityName: String) {
        lifecycleScope.launch {
            try {
                val forecast = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getFiveDayForecast(cityName, apiKey)
                }

                val grouped = groupForecastByDay(forecast.list)

                val tomorrow = grouped.values.elementAt(1).first()
                updateTomorrowCard(tomorrow)

                llForecastList.removeAllViews()
                for (i in 1 until grouped.size) {
                    addForecastRow(grouped.values.elementAt(i).first())
                }

            } catch (e: Exception) {
                Toast.makeText(this@TomorrowActivity, "Failed to load forecast", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun groupForecastByDay(list: List<ForecastListItem>): Map<String, List<ForecastListItem>> {
        val map = linkedMapOf<String, MutableList<ForecastListItem>>()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        for (item in list) {
            val date = sdf.format(Date(item.dt * 1000))
            if (!map.containsKey(date)) map[date] = mutableListOf()
            map[date]!!.add(item)
        }
        return map
    }

    private fun updateTomorrowCard(item: ForecastListItem) {
        tvTomorrowDay.text = getDay(item.dt)
        tvTomorrowCondition.text = item.weather[0].description.replaceFirstChar { it.uppercase() }
        tvTomorrowTemp.text = "${item.main.temp_max.toInt()}° / ${item.main.temp_min.toInt()}°"

        val rainPercent = ((item.pop ?: 0.0) * 100).toInt()
        tvRain.text = "$rainPercent %"
        tvWind.text = "${item.wind.speed} km/h"
        tvHumidity.text = "${item.main.humidity}%"

        imgWeatherIcon.setImageResource(getWeatherIcon(item.weather[0].icon))
    }

    private fun addForecastRow(item: ForecastListItem) {
        val row = layoutInflater.inflate(R.layout.forecast_item, llForecastList, false)
        row.findViewById<TextView>(R.id.tvDay).text = getDay(item.dt)
        row.findViewById<TextView>(R.id.tvTemp).text = "${item.main.temp_max.toInt()}° / ${item.main.temp_min.toInt()}°"
        row.findViewById<TextView>(R.id.tvWeatherDescription).text =
            item.weather[0].description.replaceFirstChar { it.uppercase() }
        row.findViewById<ImageView>(R.id.imgWeatherIcon)
            .setImageResource(getWeatherIcon(item.weather[0].icon))

        llForecastList.addView(row)
    }

    private fun getDay(timestamp: Long): String =
        SimpleDateFormat("EEEE", Locale.getDefault()).format(Date(timestamp * 1000))

    private fun getWeatherIcon(code: String): Int =
        when (code) {
            "01d" -> R.drawable.ic_sunny
            "01n" -> R.drawable.ic_clear_night
            "02d", "02n" -> R.drawable.ic_partly_cloudy
            "03d", "03n" -> R.drawable.ic_cloudy
            "04d", "04n" -> R.drawable.ic_cloudy
            "09d", "09n" -> R.drawable.ic_rainy
            "10d", "10n" -> R.drawable.ic_rainy
            "11d", "11n" -> R.drawable.ic_thunder
            "13d", "13n" -> R.drawable.ic_snow
            "50d", "50n" -> R.drawable.ic_fog
            else -> R.drawable.ic_sunny
        }
}
