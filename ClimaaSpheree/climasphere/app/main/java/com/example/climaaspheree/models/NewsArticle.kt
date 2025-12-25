package com.example.climaaspheree.models

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
)

data class NewsArticle(
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val url: String
)