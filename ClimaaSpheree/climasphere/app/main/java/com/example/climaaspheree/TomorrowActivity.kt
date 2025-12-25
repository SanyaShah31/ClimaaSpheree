package com.example.climaaspheree

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.climaaspheree.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class TomorrowActivity : AppCompatActivity() {

    private lateinit var llForecastList: LinearLayout
    private lateinit var imgWeatherIcon: ImageView
    private lateinit var tvTomorrowDay: TextView
    private lateinit var tvTomorrowCondition: TextView
    private lateinit var tvTomorrowTemp: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvVisibility: TextView

    private val apiKey = "cbbfc9fa7efe023e38d43e61ab046562"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomorrow)

        llForecastList = findViewById(R.id.llForecastList)
        imgWeatherIcon = findViewById(R.id.imgWeatherIcon)
        tvTomorrowDay = findViewById(R.id.tvTomorrowDay)
        tvTomorrowCondition = findViewById(R.id.tvTomorrowCondition)
        tvTomorrowTemp = findViewById(R.id.tvTomorrowTemp)
        tvWind = findViewById(R.id.tvWind)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvVisibility = findViewById(R.id.tvVisibility)

        val cityFromIntent = intent.getStringExtra("city_name")
        val cityName = cityFromIntent ?: getSavedCityName() ?: "London"

        fetchFiveDayForecast(cityName)
    }

    private fun getSavedCityName(): String? {
        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return prefs.getString("city_name", null)
    }

    private fun fetchFiveDayForecast(cityName: String) {
        lifecycleScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    val response = RetrofitInstance.api.getRawFiveDayForecast(cityName, apiKey)
                    JSONObject(response)
                }

                val listArray = data.getJSONArray("list")

                val dailyMap = LinkedHashMap<String, JSONObject>()

                for (i in 0 until listArray.length()) {
                    val item = listArray.getJSONObject(i)
                    val dtTxt = item.getString("dt_txt")

                    if (dtTxt.contains("12:00:00")) {
                        val day = dtTxt.substring(0, 10)
                        dailyMap[day] = item
                    }
                }

                val daysList = dailyMap.values.toList()

                if (daysList.isEmpty()) {
                    Toast.makeText(this@TomorrowActivity, "No forecast available", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                populateFiveDaysUI(daysList)

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@TomorrowActivity, "Failed to load forecast", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateFiveDaysUI(days: List<JSONObject>) {
        llForecastList.removeAllViews()

        // Tomorrow's data
        val tomorrow = days[0]
        val main = tomorrow.getJSONObject("main")
        val weather = tomorrow.getJSONArray("weather").getJSONObject(0)
        val wind = tomorrow.getJSONObject("wind")

        tvTomorrowDay.text = getDayFromTime(tomorrow.getString("dt_txt"))
        tvTomorrowTemp.text = "${main.getDouble("temp_max").toInt()}° / ${main.getDouble("temp_min").toInt()}°"
        tvTomorrowCondition.text = weather.getString("description").replaceFirstChar { it.uppercase() }
        imgWeatherIcon.setImageResource(getWeatherIcon(weather.getString("icon")))
        tvWind.text = "${wind.getDouble("speed")} km/h"
        tvHumidity.text = "${main.getInt("humidity")}%"
        tvVisibility.text = "—"

        // Add next 4 days
        for (i in 1 until days.size.coerceAtMost(5)) {
            addForecastRow(days[i])
        }
    }

    private fun addForecastRow(item: JSONObject) {
        val row = LayoutInflater.from(this).inflate(R.layout.forecast_item, llForecastList, false)

        val tvDay = row.findViewById<TextView>(R.id.tvDay)
        val tvTemp = row.findViewById<TextView>(R.id.tvTemp)
        val tvCondition = row.findViewById<TextView>(R.id.tvWeatherDescription)
        val imgWeather = row.findViewById<ImageView>(R.id.imgWeatherIcon)

        val main = item.getJSONObject("main")
        val weather = item.getJSONArray("weather").getJSONObject(0)

        tvDay.text = getDayFromTime(item.getString("dt_txt"))
        tvTemp.text = "${main.getDouble("temp_max").toInt()}° / ${main.getDouble("temp_min").toInt()}°"
        tvCondition.text = weather.getString("description").replaceFirstChar { it.uppercase() }
        imgWeather.setImageResource(getWeatherIcon(weather.getString("icon")))

        llForecastList.addView(row)
    }

    private fun getDayFromTime(text: String): String {
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(text)
        return sdf.format(date!!)
    }

    private fun getWeatherIcon(iconCode: String?): Int {
        return when (iconCode) {
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
}
