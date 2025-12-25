package com.example.climaaspheree.utils

import android.content.Context

class NotificationPref(context: Context) {

    private val pref = context.getSharedPreferences("alerts_pref", Context.MODE_PRIVATE)

    fun saveNotification(alert: String) {
        val oldList = pref.getStringSet("ALERT_LIST", HashSet()) ?: HashSet()
        val newList = HashSet<String>()
        newList.add("${System.currentTimeMillis()}||$alert")  // with timestamp
        newList.addAll(oldList)
        pref.edit().putStringSet("ALERT_LIST", newList).apply()
    }

    fun getNotifications(): List<Pair<Long, String>> {
        val set = pref.getStringSet("ALERT_LIST", emptySet()) ?: emptySet()
        return set.map {
            val split = it.split("||")
            split[0].toLong() to split[1]
        }.sortedByDescending { it.first }
    }

    fun deleteNotification(item: Pair<Long, String>) {
        val list = pref.getStringSet("ALERT_LIST", HashSet())!!.toMutableSet()
        list.remove("${item.first}||${item.second}")
        pref.edit().putStringSet("ALERT_LIST", list).apply()
    }

    fun clearAll() {
        pref.edit().remove("ALERT_LIST").apply()
    }
}
