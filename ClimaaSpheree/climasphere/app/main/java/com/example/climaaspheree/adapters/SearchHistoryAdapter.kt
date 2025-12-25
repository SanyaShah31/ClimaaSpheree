package com.example.climaaspheree.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.climaaspheree.R

class SearchHistoryAdapter(
    private var list: MutableList<String>,
    private val onClick: (String) -> Unit,
    private val onDelete: (String) -> Unit
) : RecyclerView.Adapter<SearchHistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val txtCity: TextView = item.findViewById(R.id.txtHistoryItem)
        val btnDelete: ImageView = item.findViewById(R.id.btnDeleteHistory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val city = list[position]
        holder.txtCity.text = city

        holder.itemView.setOnClickListener { onClick(city) }
        holder.btnDelete.setOnClickListener {
            onDelete(city)
        }
    }

    fun update(newList: List<String>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }
}
