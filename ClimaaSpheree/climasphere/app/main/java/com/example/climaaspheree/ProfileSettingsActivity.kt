package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.climaaspheree.data.local.SettingsManager
import com.example.climaaspheree.utils.TemperatureUnitManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileSettingsActivity : AppCompatActivity() {

    private lateinit var switchTheme: Switch
    private lateinit var switchTempUnit: Switch
    private lateinit var txtUserEmail: TextView
    private lateinit var txtUserName: TextView
    private lateinit var btnBackDashboard: Button
    private lateinit var btnSignOut: Button
    private lateinit var settingsManager: SettingsManager
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_settings)

        // Initialize views
        txtUserName = findViewById(R.id.tvFullName)
        txtUserEmail = findViewById(R.id.tvEmail)
        switchTheme = findViewById(R.id.switchTheme)
        switchTempUnit = findViewById(R.id.switchTempUnit)
        btnBackDashboard = findViewById(R.id.btnBackDashboard)
        btnSignOut = findViewById(R.id.btnSignOut)

        // Initialize DataStore SettingsManager
        settingsManager = SettingsManager(this)

        // Load user info
        val user = auth.currentUser
        txtUserEmail.text = user?.email ?: "Not available"
        txtUserName.text = user?.displayName ?: "User"

        // Load and apply saved settings
        loadSettings()

        // Back to Dashboard
        btnBackDashboard.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Sign Out
        btnSignOut.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Theme Toggle
        switchTheme.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            lifecycleScope.launch { settingsManager.saveThemeSetting(isChecked) }
            applyTheme(isChecked)
        }

        // Temperature Unit Toggle (°C / °F)
        switchTempUnit.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            lifecycleScope.launch { settingsManager.saveTemperatureUnit(isChecked) }
            TemperatureUnitManager.isDualUnitEnabled = isChecked
        }
    }

    private fun loadSettings() {
        lifecycleScope.launch {
            val isDarkMode = settingsManager.isDarkMode.first()
            val showBothUnits = settingsManager.showBothUnits.first()

            switchTheme.isChecked = isDarkMode
            switchTempUnit.isChecked = showBothUnits

            applyTheme(isDarkMode)
        }
    }

    private fun applyTheme(isDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        val root = findViewById<android.view.View>(android.R.id.content)
        root.setBackgroundColor(
            if (isDarkMode) getColor(R.color.neavy_blue)
            else getColor(R.color.light_blue)
        )
    }
}
