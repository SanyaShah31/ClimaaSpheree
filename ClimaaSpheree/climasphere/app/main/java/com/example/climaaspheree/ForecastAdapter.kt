package com.example.climaaspheree.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.climaaspheree.R
import com.example.climaaspheree.network.ForecastItem
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(private val forecastList: List<ForecastItem>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDay: TextView = itemView.findViewById(R.id.tvDay)
        val tvTemp: TextView = itemView.findViewById(R.id.tvTemp)
        val tvWeatherDescription: TextView = itemView.findViewById(R.id.tvWeatherDescription)
        val imgWeatherIcon: ImageView = itemView.findViewById(R.id.imgWeatherIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = forecastList[position]

        // Day name
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        holder.tvDay.text = sdf.format(Date(item.dt * 1000))

        // Temperature (min/max for free plan)
        holder.tvTemp.text = "${item.temp.max.toInt()}° / ${item.temp.min.toInt()}°"

        // Weather description
        val weather = item.weather.firstOrNull()
        holder.tvWeatherDescription.text =
            weather?.description?.replaceFirstChar { it.uppercase() } ?: "Clear"

        // Icon
        when (weather?.main?.lowercase(Locale.getDefault())) {
            "clear" -> holder.imgWeatherIcon.setImageResource(R.drawable.ic_sunny)
            "rain" -> holder.imgWeatherIcon.setImageResource(R.drawable.ic_rainy)
            "clouds" -> holder.imgWeatherIcon.setImageResource(R.drawable.ic_cloudy)
            "snow" -> holder.imgWeatherIcon.setImageResource(R.drawable.ic_snow)
            else -> holder.imgWeatherIcon.setImageResource(R.drawable.ic_cloudy)
        }
    }

    override fun getItemCount(): Int = forecastList.size
}
