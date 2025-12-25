package com.example.climaaspheree.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// Create the DataStore
val Context.dataStore by preferencesDataStore(name = "app_settings")

class SettingsManager(private val context: Context) {

    companion object {
        // Preference Keys
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        private val SHOW_BOTH_UNITS_KEY = booleanPreferencesKey("show_both_units")
        private val NOTIFICATION_KEY = booleanPreferencesKey("notifications_enabled")
    }

    // Theme mode (Light Blue / Navy Blue)
    val isDarkMode: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[DARK_MODE_KEY] ?: false
    }

    // Temperature display mode:
    // true → Show both °C + °F
    // false → Show only °C
    val showBothUnits: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[SHOW_BOTH_UNITS_KEY] ?: false
    }

    // Weather alerts notification
    val notificationsEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[NOTIFICATION_KEY] ?: true
    }

    // Save theme (dark = navy blue, light = sky blue)
    suspend fun saveThemeSetting(isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DARK_MODE_KEY] = isDark
        }
    }

    // Save temperature unit setting
    suspend fun saveTemperatureUnit(showBoth: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[SHOW_BOTH_UNITS_KEY] = showBoth
        }
    }

    // Save notification toggle state
    suspend fun saveNotificationSetting(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[NOTIFICATION_KEY] = enabled
        }
    }

    // Clear all user settings (optional helper)
    suspend fun resetAllSettings() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
