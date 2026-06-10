package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.climaaspheree.adapters.NewsAdapter
import com.example.climaaspheree.models.NewsArticle
import com.example.climaaspheree.models.NewsResponse
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class NewsActivity : AppCompatActivity() {

    private lateinit var recyclerNews: RecyclerView
    private lateinit var btnBackDashboard: LinearLayout
    private lateinit var searchInputLayout: SearchView
    private lateinit var progressBar: ProgressBar
    private lateinit var newsAdapter: NewsAdapter
    private var newsList = mutableListOf<NewsArticle>()

    private val client = OkHttpClient()
    private val apiKey = "aaa59f3a37054eee98fdc2f7e1cda38a" // Replace with your API key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        recyclerNews = findViewById(R.id.recyclerNews)
        btnBackDashboard = findViewById(R.id.btnBackDashboard)
        searchInputLayout = findViewById(R.id.searchInputLayout)
        progressBar = findViewById(R.id.progressBar)

        recyclerNews.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(this, newsList)
        recyclerNews.adapter = newsAdapter

        btnBackDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        // ✅ Load all weather news by default
        fetchWeatherNews("weather OR climate OR temperature OR rainfall OR storm")

        // ✅ Make search text visible and set color properly
        searchInputLayout.isIconified = false
        searchInputLayout.clearFocus()

        // ✅ Force search text color (for light/dark theme visibility)
        val id = searchInputLayout.context.resources.getIdentifier("android:id/search_src_text", null, null)
        val searchText = searchInputLayout.findViewById<android.widget.EditText>(id)
        searchText?.setTextColor(resources.getColor(android.R.color.black, theme))
        searchText?.setHintTextColor(resources.getColor(android.R.color.darker_gray, theme))

        // ✅ Search listener
        searchInputLayout.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    fetchWeatherNews(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    fetchWeatherNews("weather OR climate OR temperature OR rainfall OR storm")
                }
                return true
            }
        })
    }

    private fun fetchWeatherNews(query: String) {
        runOnUiThread { progressBar.visibility = View.VISIBLE }

        val url =
            "https://newsapi.org/v2/everything?q=${query.trim()}&language=en&sortBy=publishedAt&domains=bbc.com,cnn.com,weather.com,accuweather.com,reuters.com&apiKey=$apiKey"

        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@NewsActivity, "Failed to load news", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { body ->
                    val newsResponse = Gson().fromJson(body, NewsResponse::class.java)
                    runOnUiThread {
                        progressBar.visibility = View.GONE
                        newsList.clear()

                        // ✅ Remove duplicates
                        val uniqueArticles = newsResponse.articles.distinctBy { it.title }

                        // ✅ Add only weather-related news
                        val filtered = uniqueArticles.filter {
                            it.title.contains("weather", true) ||
                                    it.description?.contains("weather", true) == true ||
                                    it.title.contains("climate", true) ||
                                    it.title.contains("storm", true) ||
                                    it.title.contains("rainfall", true) ||
                                    it.title.contains("temperature", true)
                        }

                        newsList.addAll(filtered)
                        newsAdapter.notifyDataSetChanged()

                        if (newsList.isEmpty()) {
                            Toast.makeText(this@NewsActivity, "No weather news found", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }
}
