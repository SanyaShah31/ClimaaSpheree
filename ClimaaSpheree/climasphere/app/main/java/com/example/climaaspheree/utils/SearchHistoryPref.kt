package com.example.climaaspheree.utils

import android.content.Context

class SearchHistoryPref(private val context: Context) {

    private val prefs = context.getSharedPreferences("history_pref", Context.MODE_PRIVATE)

    fun saveSearch(city: String) {
        val set = prefs.getStringSet("history", LinkedHashSet())!!.toMutableSet()
        set.add(city)
        prefs.edit().putStringSet("history", set).apply()
    }

    fun getSearches(): List<String> {
        return prefs.getStringSet("history", emptySet())!!.toList().reversed()
    }

    fun deleteSearch(city: String) {
        val set = prefs.getStringSet("history", LinkedHashSet())!!.toMutableSet()
        set.remove(city)
        prefs.edit().putStringSet("history", set).apply()
    }
}
