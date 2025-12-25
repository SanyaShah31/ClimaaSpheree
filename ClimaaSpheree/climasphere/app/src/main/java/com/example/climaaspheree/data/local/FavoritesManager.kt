package com.example.climaaspheree.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.favoritesDataStore by preferencesDataStore(name = "favorite_cities")

class FavoritesManager(private val context: Context) {

    companion object {
        private val FAVORITES_KEY = stringSetPreferencesKey("favorite_cities_list")
    }

    /** Flow that continuously emits current favorites */
    val favoriteCities: Flow<Set<String>> = context.favoritesDataStore.data.map { prefs ->
        prefs[FAVORITES_KEY] ?: emptySet()
    }

    /** Add a new favorite city */
    suspend fun addCity(city: String) {
        context.favoritesDataStore.edit { prefs ->
            val current = prefs[FAVORITES_KEY]?.toMutableSet() ?: mutableSetOf()
            current.add(city)
            prefs[FAVORITES_KEY] = current // ✅ commit updated set
        }
    }

    /** Remove a favorite city */
    suspend fun removeCity(city: String) {
        context.favoritesDataStore.edit { prefs ->
            val current = prefs[FAVORITES_KEY]?.toMutableSet() ?: mutableSetOf()
            current.remove(city)
            prefs[FAVORITES_KEY] = current // ✅ persist changes
        }
    }

    /** Clear all favorites (optional helper) */
    suspend fun clearAll() {
        context.favoritesDataStore.edit { prefs ->
            prefs.remove(FAVORITES_KEY)
        }
    }
}
