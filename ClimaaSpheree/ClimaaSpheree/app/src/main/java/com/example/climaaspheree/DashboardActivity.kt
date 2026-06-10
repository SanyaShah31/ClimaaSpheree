package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.climaaspheree.adapters.SearchHistoryAdapter
import com.example.climaaspheree.data.local.FavoritesManager
import com.example.climaaspheree.data.local.SettingsManager
import com.example.climaaspheree.databinding.ActivityDashboardBinding
import com.example.climaaspheree.utils.NotificationHelper
import com.example.climaaspheree.utils.SearchHistoryPref
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var favoritesManager: FavoritesManager
    private lateinit var settingsManager: SettingsManager
    private lateinit var historyPref: SearchHistoryPref

    private var searchedCity: String = "London"
    private var isFavorite = false

    private var interstitialAd: InterstitialAd? = null
    private lateinit var historyAdapter: SearchHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoritesManager = FavoritesManager(this)
        settingsManager = SettingsManager(this)
        historyPref = SearchHistoryPref(this)

        MobileAds.initialize(this)
        loadBannerAd()
        loadInterstitialAd()

        lifecycleScope.launch {
            NotificationHelper.isNotificationsEnabled = settingsManager.notificationsEnabled.first()
        }

        setupRecycler()
        setupSearchFeature()
        setupCardClicks()
        setupFavoriteButton()
        updateFavoriteButton()
        loadHistory()
    }

    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        binding.adViewDashboard.loadAd(adRequest)
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) { interstitialAd = ad }
                override fun onAdFailedToLoad(error: LoadAdError) { interstitialAd = null }
            })
    }

    private fun showInterstitialAd(onFinish: () -> Unit) {
        if (interstitialAd != null) {
            interstitialAd?.show(this)
            interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    interstitialAd = null
                    loadInterstitialAd()
                    onFinish()
                }
            }
        } else onFinish()
    }

    private fun setupRecycler() {
        historyAdapter = SearchHistoryAdapter(
            mutableListOf(),
            onClick = { city ->
                searchedCity = city
                binding.searchCity.setText(city)
                binding.historyRecycler.visibility = View.GONE
            },
            onDelete = { city ->
                historyPref.deleteSearch(city)
                loadHistory()
            }
        )

        binding.historyRecycler.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = historyAdapter
            visibility = View.GONE
        }
    }

    private fun setupSearchFeature() {

        binding.searchCity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s.toString()
                val all = historyPref.getSearches()
                val filtered = all.filter {
                    it.contains(text, ignoreCase = true)
                }

                if (text.isEmpty() || filtered.isEmpty()) {
                    binding.historyRecycler.visibility = View.GONE
                } else {
                    historyAdapter.update(filtered)
                    binding.historyRecycler.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnSearch.setOnClickListener {
            val city = binding.searchCity.text.toString().trim()
            if (city.isNotEmpty()) {
                searchedCity = city
                binding.tvCurrentWeatherData.text = "Weather in $city: Tap for details"
                binding.tvWeatherHint.text = "Tap city name to view live weather"

                historyPref.saveSearch(city)
                loadHistory()
                updateFavoriteButton()
                binding.historyRecycler.visibility = View.GONE
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
            }
        }

        binding.searchCity.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.btnSearch.performClick()
                true
            } else false
        }

        binding.tvCurrentWeatherTitle.setOnClickListener {
            showInterstitialAd {
                val intent = Intent(this, ForecastActivity::class.java)
                intent.putExtra("city_name", searchedCity)
                startActivity(intent)
            }
        }
    }

    private fun setupCardClicks() {
        binding.cardToday.setOnClickListener {
            showInterstitialAd {
                startActivity(Intent(this, TodayActivity::class.java).putExtra("city_name", searchedCity))
            }
        }

        binding.cardForecast.setOnClickListener {
            // Show ad THEN open other app TomorrowActivity
            showInterstitialAd {
                try {
                    val intent = Intent().apply {
                        setClassName(
                            "com.example.climasphere2", // other app package
                            "com.example.climasphere2.TomorrowActivity" // other app activity
                        )
                        putExtra("city_name", searchedCity)
                    }
                    startActivity(intent)

                } catch (e: Exception) {
                    Toast.makeText(this, "Tomorrow app not installed!", Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.cardAlerts.setOnClickListener { startActivity(Intent(this, AlertsActivity::class.java)) }
        binding.cardMaps.setOnClickListener { startActivity(Intent(this, MapActivity::class.java)) }
        binding.cardNews.setOnClickListener { startActivity(Intent(this, NewsActivity::class.java)) }
        binding.cardFav.setOnClickListener { startActivity(Intent(this, FavoritesActivity::class.java)) }
        binding.cardProfile.setOnClickListener { startActivity(Intent(this, ProfileSettingsActivity::class.java)) }
    }

    private fun setupFavoriteButton() {
        binding.btnAddToFavorites.setOnClickListener {
            val city = searchedCity.trim()
            if (city.isEmpty()) {
                Toast.makeText(this, "Please search a city first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val currentFavorites = favoritesManager.favoriteCities.first().toMutableSet()
                if (currentFavorites.contains(city)) {
                    favoritesManager.removeCity(city)
                    isFavorite = false
                    Toast.makeText(this@DashboardActivity, "$city removed 💔", Toast.LENGTH_SHORT).show()
                } else {
                    favoritesManager.addCity(city)
                    isFavorite = true
                    Toast.makeText(this@DashboardActivity, "$city added ❤", Toast.LENGTH_SHORT).show()
                }
                updateFavoriteButton()
            }
        }
    }

    private fun updateFavoriteButton() {
        lifecycleScope.launch {
            val favorites = favoritesManager.favoriteCities.first()
            isFavorite = favorites.contains(searchedCity)

            binding.btnAddToFavorites.setImageResource(
                if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )
            binding.btnAddToFavorites.setColorFilter(
                if (isFavorite) getColor(android.R.color.holo_red_light) else getColor(android.R.color.white)
            )
        }
    }

    private fun loadHistory() {
        val list = historyPref.getSearches()
        historyAdapter.update(list)
    }
}
