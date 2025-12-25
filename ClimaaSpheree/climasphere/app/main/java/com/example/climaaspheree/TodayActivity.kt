package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.climaaspheree.data.local.SettingsManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class TodayActivity : AppCompatActivity() {

    private val apiKey = "cbbfc9fa7efe023e38d43e61ab046562"
    private lateinit var settingsManager: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today)

        settingsManager = SettingsManager(this)

        val tvCity = findViewById<TextView>(R.id.tvCity)
        val tvDate = findViewById<TextView>(R.id.tvDate)
        val tvCondition = findViewById<TextView>(R.id.tvCondition)
        val tvTemperature = findViewById<TextView>(R.id.tvTemperature)
        val tvHighLow = findViewById<TextView>(R.id.tvHighLow)
        val tvRainValue = findViewById<TextView>(R.id.tvRainValue)
        val tvWindValue = findViewById<TextView>(R.id.tvWindValue)
        val tvHumidityValue = findViewById<TextView>(R.id.tvHumidityValue)

        val imgWeatherIcon = findViewById<ImageView>(R.id.imgWeatherIcon)
        val rootLayout = findViewById<LinearLayout>(R.id.rootLinear)

        val tvHour7 = findViewById<TextView>(R.id.tvHour7)
        val tvHour8 = findViewById<TextView>(R.id.tvHour8)
        val tvHour9 = findViewById<TextView>(R.id.tvHour9)
        val tvHour10 = findViewById<TextView>(R.id.tvHour10)

        val imgHour7 = findViewById<ImageView>(R.id.imgHour7)
        val imgHour8 = findViewById<ImageView>(R.id.imgHour8)
        val imgHour9 = findViewById<ImageView>(R.id.imgHour9)
        val imgHour10 = findViewById<ImageView>(R.id.imgHour10)

        val tvTemp7 = findViewById<TextView>(R.id.tvTemp7)
        val tvTemp8 = findViewById<TextView>(R.id.tvTemp8)
        val tvTemp9 = findViewById<TextView>(R.id.tvTemp9)
        val tvTemp10 = findViewById<TextView>(R.id.tvTemp10)

        val btnNext7 = findViewById<TextView>(R.id.tvNext7)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        val city = intent.getStringExtra("city") ?: "London"
        tvCity.text = city
        tvDate.text = SimpleDateFormat("EEE, MMM dd", Locale.getDefault()).format(Date())

//        btnNext7.setOnClickListener {
//            val intent = Intent(this, TomorrowActivity::class.java)
//            intent.putExtra("city", city)
//            startActivity(intent)
//        }

        btnNext7.setOnClickListener {
            try {
                val intent = Intent().apply {
                    // Switch to other app (Climasphere2)
                    setClassName(
                        "com.example.climasphere2",
                        "com.example.climasphere2.TomorrowActivity"
                    )
                    putExtra("city_name", city)   // use correct key
                }
                startActivity(intent)

            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "Tomorrow page app (Climasphere2) not installed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        btnBack.setOnClickListener { finish() }

        lifecycleScope.launch {
            val showUnits = settingsManager.showBothUnits.first()
            fetchWeather(
                city,
                showUnits,
                tvCondition,
                tvTemperature,
                tvHighLow,
                tvRainValue,
                tvWindValue,
                tvHumidityValue,
                imgWeatherIcon,
                rootLayout,
                tvHour7, tvHour8, tvHour9, tvHour10,
                imgHour7, imgHour8, imgHour9, imgHour10,
                tvTemp7, tvTemp8, tvTemp9, tvTemp10
            )
        }
    }

    // ⭐ UPDATED FUNCTION WITH HOURLY + BACKGROUND UPDATE
    private suspend fun fetchWeather(
        city: String,
        showUnits: Boolean,
        tvCondition: TextView,
        tvTemperature: TextView,
        tvHighLow: TextView,
        tvRain: TextView,
        tvWind: TextView,
        tvHumidity: TextView,
        imgWeatherIcon: ImageView,
        rootLayout: LinearLayout,
        tvHour7: TextView, tvHour8: TextView, tvHour9: TextView, tvHour10: TextView,
        imgHour7: ImageView, imgHour8: ImageView, imgHour9: ImageView, imgHour10: ImageView,
        tvTemp7: TextView, tvTemp8: TextView, tvTemp9: TextView, tvTemp10: TextView
    ) {
        withContext(Dispatchers.IO) {
            try {
                // ---------- MAIN WEATHER ----------
                val response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$apiKey").readText()

                val json = JSONObject(response)
                val main = json.getJSONObject("main")
                val weather = json.getJSONArray("weather").getJSONObject(0)
                val wind = json.getJSONObject("wind")
                val coord = json.getJSONObject("coord")

                val temp = main.getDouble("temp")
                val tempMin = main.getDouble("temp_min")
                val tempMax = main.getDouble("temp_max")
                val humidity = main.getInt("humidity")
                val condition = weather.getString("description")
                val windSpeed = wind.getDouble("speed")

                // 🌧 Rain
                var rainValue = "0%"
                if (json.has("rain")) {
                    val rainObject = json.getJSONObject("rain")
                    val oneHourRain = rainObject.optDouble("1h", 0.0)
                    rainValue = "${(oneHourRain * 10).toInt()}%"
                }

                // ---------- HOURLY WEATHER ----------
                val lat = coord.getDouble("lat")
                val lon = coord.getDouble("lon")

                val hourlyRes =
                    URL("https://api.openweathermap.org/data/2.5/forecast?lat=$lat&lon=$lon&units=metric&appid=$apiKey")
                        .readText()

                val hourlyJson = JSONObject(hourlyRes).getJSONArray("list")

                val h7 = hourlyJson.getJSONObject(0)
                val h8 = hourlyJson.getJSONObject(1)
                val h9 = hourlyJson.getJSONObject(2)
                val h10 = hourlyJson.getJSONObject(3)

                val hourlyData = listOf(h7, h8, h9, h10)

                withContext(Dispatchers.Main) {

                    // ⭐ UPDATE BACKGROUND + MAIN ICON
                    updateBackground(condition, imgWeatherIcon, rootLayout)

                    // Main weather UI
                    tvCondition.text = condition.replaceFirstChar { it.uppercase() }
                    tvHumidity.text = "$humidity%"
                    tvWind.text = "$windSpeed m/s"
                    tvRain.text = rainValue

                    if (showUnits) {
                        val f = (temp * 9 / 5) + 32
                        val maxF = (tempMax * 9 / 5) + 32
                        val minF = (tempMin * 9 / 5) + 32
                        tvTemperature.text = "${temp.toInt()}°C / ${f.toInt()}°F"
                        tvHighLow.text =
                            "H:${tempMax.toInt()}°C/${maxF.toInt()}°F  L:${tempMin.toInt()}°C/${minF.toInt()}°F"
                    } else {
                        tvTemperature.text = "${temp.toInt()}°C"
                        tvHighLow.text = "H:${tempMax.toInt()}°C  L:${tempMin.toInt()}°C"
                    }

                    // ⭐ HOURLY WEATHER UPDATE
                    val hourViews = listOf(tvTemp7, tvTemp8, tvTemp9, tvTemp10)
                    val iconViews = listOf(imgHour7, imgHour8, imgHour9, imgHour10)

                    for (i in 0..3) {
                        val obj = hourlyData[i]
                        val t = obj.getJSONObject("main").getDouble("temp").toInt()
                        val icon = obj.getJSONArray("weather").getJSONObject(0).getString("main")

                        hourViews[i].text = "$t°"
                        iconViews[i].setImageResource(getWeatherIcon(icon))
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@TodayActivity, "Error fetching data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    // ⭐ WEATHER ICONS
    private fun getWeatherIcon(condition: String): Int {
        return when {
            condition.contains("Rain", true) -> R.drawable.ic_rainy
            condition.contains("Cloud", true) -> R.drawable.ic_cloudy
            condition.contains("Clear", true) -> R.drawable.ic_sunny
            else -> R.drawable.ic_cloudy
        }
    }

    // ⭐ BACKGROUND + MAIN ICON
    private fun updateBackground(condition: String, iconView: ImageView, root: LinearLayout) {

        when {
            condition.contains("rain", true) -> {
                iconView.setImageResource(R.drawable.ic_rainy)
                root.setBackgroundResource(R.drawable.bg_gradient_sky_dash)
            }
            condition.contains("cloud", true) -> {
                iconView.setImageResource(R.drawable.ic_cloudy)
                root.setBackgroundResource(R.drawable.bg_gradient_sky_dash)
            }
            condition.contains("clear", true) -> {
                iconView.setImageResource(R.drawable.ic_sunny)
                root.setBackgroundResource(R.drawable.bg_gradient_sky_dash)
            }
            else -> {
                iconView.setImageResource(R.drawable.ic_cloudy)
                root.setBackgroundResource(R.drawable.bg_gradient_sky_dash)
            }
        }
    }
}
