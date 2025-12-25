package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.climaaspheree.data.local.SettingsManager
import com.example.climaaspheree.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashDelay = 2200L
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        navigateToLogin()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Apply saved theme from DataStore before showing UI
        lifecycleScope.launch {
            val settingsManager = SettingsManager(this@SplashActivity)
            val isDarkMode = settingsManager.isDarkMode.first()

            AppCompatDelegate.setDefaultNightMode(
                if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )

            // Continue setup after theme is applied
            setupSplash()
        }
    }

    private fun setupSplash() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Auto navigate after delay
        handler.postDelayed(runnable, splashDelay)

        // Skip button
        binding.getStartedBtn.setOnClickListener {
            handler.removeCallbacks(runnable)
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            // User already logged in → Go to Dashboard
            startActivity(Intent(this, DashboardActivity::class.java))
        } else {
            // User not logged in → Open Login
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}
