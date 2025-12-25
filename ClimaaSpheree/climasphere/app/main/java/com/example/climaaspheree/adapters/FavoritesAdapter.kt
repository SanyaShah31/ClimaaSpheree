package com.example.climaaspheree.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.climaaspheree.R
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL

class FavoritesAdapter(
    private val favoriteCities: MutableList<String>
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    private val apiKey = "cbbfc9fa7efe023e38d43e61ab046562"

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCityName: TextView = itemView.findViewById(R.id.tvCityName)
        val tvCityTemp: TextView = itemView.findViewById(R.id.tvCityTemp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_city, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val city = favoriteCities[position]
        holder.tvCityName.text = city
        holder.tvCityTemp.text = "Loading..."

        // Fetch current temperature for this city
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$apiKey")
                        .readText()
                val json = JSONObject(response)
                val temp = json.getJSONObject("main").getDouble("temp")

                withContext(Dispatchers.Main) {
                    holder.tvCityTemp.text = "${temp.toInt()}°C"
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    holder.tvCityTemp.text = "--°C"
                }
            }
        }
    }

    override fun getItemCount(): Int = favoriteCities.size
}
