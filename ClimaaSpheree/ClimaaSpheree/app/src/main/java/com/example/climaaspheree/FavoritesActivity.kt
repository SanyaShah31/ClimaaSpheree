package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.climaaspheree.data.local.FavoritesManager
import com.example.climaaspheree.adapters.FavoritesAdapter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FavoritesActivity : AppCompatActivity() {

    private lateinit var rvFavorites: RecyclerView
    private lateinit var favoritesManager: FavoritesManager
    private lateinit var btnBackDashboard: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        rvFavorites = findViewById(R.id.rvFavorites)
        btnBackDashboard = findViewById(R.id.btnBackDashboard)
        favoritesManager = FavoritesManager(this)

        btnBackDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        loadFavorites()
    }

    /** 🌤 Loads favorite cities dynamically from DataStore */
    private fun loadFavorites() {
        val emptyView = findViewById<View>(R.id.favoritesContainer)

        lifecycleScope.launch {
            val favoritesList = favoritesManager.favoriteCities.first().toMutableList() // ✅ convert safely

            if (favoritesList.isEmpty()) {
                rvFavorites.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.GONE
                rvFavorites.visibility = View.VISIBLE
                rvFavorites.layoutManager = LinearLayoutManager(this@FavoritesActivity)
                rvFavorites.adapter = FavoritesAdapter(favoritesList)
            }
        }
    }
}
