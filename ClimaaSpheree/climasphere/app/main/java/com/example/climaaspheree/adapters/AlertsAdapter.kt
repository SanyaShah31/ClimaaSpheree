package com.example.climaaspheree.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.climaaspheree.R
import com.example.climaaspheree.network.WeatherAlert

class AlertsAdapter(private val alerts: List<WeatherAlert>) :
    RecyclerView.Adapter<AlertsAdapter.AlertViewHolder>() {

    class AlertViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val alertTitle: TextView = view.findViewById(R.id.alertTitle)
        val alertDesc: TextView = view.findViewById(R.id.alertDesc)
        val alertSource: TextView = view.findViewById(R.id.alertSource)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alert_card, parent, false)
        return AlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        val alert = alerts[position]
        holder.alertTitle.text = alert.event
        holder.alertDesc.text = alert.description
        holder.alertSource.text = "From: ${alert.sender_name}"
    }

    override fun getItemCount(): Int = alerts.size
}
