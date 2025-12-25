package com.example.climaaspheree.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climaaspheree.network.ForecastRepository
import com.example.climaaspheree.network.ForecastResponse
import kotlinx.coroutines.launch

class ForecastViewModel(private val repository: ForecastRepository) : ViewModel() {

    private val _forecast = MutableLiveData<ForecastResponse>()
    val forecast: LiveData<ForecastResponse> = _forecast

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    /**
     * Fetch forecast using latitude and longitude
     */
    fun fetchForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                // call repository
                val response = repository.getSevenDayForecast(lat, lon)

                // If repository returns a Result, extract it; if it returns ForecastResponse directly, just post
                _forecast.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message ?: "Unknown error")
            }
        }
    }
}
