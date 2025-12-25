package com.example.climaaspheree.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.climaaspheree.R
import com.example.climaaspheree.models.NewsArticle
import com.squareup.picasso.Picasso

class NewsAdapter(private val context: Context, private val newsList: List<NewsArticle>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgNews: ImageView = view.findViewById(R.id.imgNews)
        val tvTitle: TextView = view.findViewById(R.id.tvNewsTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvNewsDescription)
        val btnReadMore: TextView = view.findViewById(R.id.btnReadMore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.tvTitle.text = news.title
        holder.tvDescription.text = news.description ?: "No description available"

        if (!news.urlToImage.isNullOrEmpty()) {
            Picasso.get().load(news.urlToImage).into(holder.imgNews)
        } else {
            holder.imgNews.setImageResource(R.drawable.ic_clear_night)
        }

        holder.btnReadMore.setOnClickListener {
            val articleUrl = news.url
            if (!articleUrl.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(articleUrl))
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "No article URL available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = newsList.size
}
