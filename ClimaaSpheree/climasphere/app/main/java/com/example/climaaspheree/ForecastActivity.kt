package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.airbnb.lottie.LottieAnimationView
import com.example.climaaspheree.data.local.SettingsManager
import com.example.climaaspheree.network.RetrofitInstance
import com.example.climaaspheree.utils.SearchHistoryPref
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ForecastActivity : AppCompatActivity() {

    private lateinit var tvCityName: TextView
    private lateinit var tvTemp: TextView
    private lateinit var tvDesc: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvSunrise: TextView
    private lateinit var tvSunset: TextView
    private lateinit var currentWeatherAnim: LottieAnimationView
    private lateinit var bgImage: ImageView
    private lateinit var rootLayout: ConstraintLayout
    private lateinit var btnBack: ImageView

    private val apiKey = "cbbfc9fa7efe023e38d43e61ab046562"
    private lateinit var settingsManager: SettingsManager
    //added new for search history
    private lateinit var historyPref: SearchHistoryPref
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        settingsManager = SettingsManager(this)
        // search history
        historyPref = SearchHistoryPref(this)


        rootLayout = findViewById(R.id.rootLayout)
        tvCityName = findViewById(R.id.tvCityName)
        tvTemp = findViewById(R.id.tvTemp)
        tvDesc = findViewById(R.id.tvDesc)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvWind = findViewById(R.id.tvWind)
        tvSunrise = findViewById(R.id.tvSunrise)
        tvSunset = findViewById(R.id.tvSunset)
        currentWeatherAnim = findViewById(R.id.currentWeatherAnim)
        bgImage = findViewById(R.id.bgImage)
        btnBack = findViewById(R.id.btnBack)

        val cityFromIntent = intent.getStringExtra("city_name") ?: "London"

        lifecycleScope.launch {
            val isDark = settingsManager.isDarkMode.first()
            val showBothUnits = settingsManager.showBothUnits.first()
            applyTheme(isDark)
            fetchWeather(cityFromIntent, showBothUnits)
        }

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        tvCityName.setOnClickListener {
            val intent = Intent(this, TodayActivity::class.java)
            intent.putExtra("city", tvCityName.text.toString())
            startActivity(intent)
        }

        // ❌ Removed the etCity listener because search bar was removed

        scheduleWeatherUpdate()
    }

    private suspend fun fetchWeather(city: String, showBothUnits: Boolean) {
        withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getCurrentWeather(city, apiKey)
                val tempC = response.main.temp
                val tempF = (tempC * 9 / 5) + 32

                withContext(Dispatchers.Main) {
                    tvCityName.text = response.name
                    tvTemp.text = if (showBothUnits) {
                        "${tempC.toInt()}°C / ${tempF.toInt()}°F"
                    } else {
                        "${tempC.toInt()}°C"
                    }
                    tvDesc.text = response.weather[0].description.replaceFirstChar { it.uppercase() }
                    tvHumidity.text = "Humidity: ${response.main.humidity}%"
                    tvWind.text = "Wind: ${response.wind.speed} m/s"
                    tvSunrise.text = formatTime(response.sys.sunrise)
                    tvSunset.text = formatTime(response.sys.sunset)
                    updateWeatherVisual(response.weather[0].main)

                    // ⭐ Save to search history when forecast loads successfully
                    historyPref.saveSearch(response.name)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ForecastActivity, "Error fetching forecast", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateWeatherVisual(condition: String) {

        rootLayout.setBackgroundResource(R.drawable.bg_gradient_sky_dash)

        val lower = condition.lowercase(Locale.getDefault())

        when {
            "clear" in lower -> currentWeatherAnim.setAnimation(R.raw.lottie_weather_sunny)
            "rain" in lower -> currentWeatherAnim.setAnimation(R.raw.lottie_weather_rainy)
            "cloud" in lower -> currentWeatherAnim.setAnimation(R.raw.lottie_weather_cloudy)
            "snow" in lower -> currentWeatherAnim.setAnimation(R.raw.lottie_weather_snowy)
            else -> currentWeatherAnim.setAnimation(R.raw.app_logo)
        }

        currentWeatherAnim.playAnimation()
    }

    private fun formatTime(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(date)
    }

    private fun applyTheme(isDark: Boolean) {
        val bgColor = if (isDark) R.color.bg_dark else R.color.bg_light
        rootLayout.setBackgroundColor(ContextCompat.getColor(this, bgColor))
    }

    private fun scheduleWeatherUpdate() {
        val workRequest =
            PeriodicWorkRequestBuilder<WeatherUpdateWorker>(6, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "weather_update_work",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}
