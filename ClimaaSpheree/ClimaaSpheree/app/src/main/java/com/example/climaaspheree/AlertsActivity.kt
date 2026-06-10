package com.example.climaaspheree

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.climaaspheree.utils.NotificationHelper
import com.example.climaaspheree.utils.NotificationPref
import java.text.SimpleDateFormat
import java.util.Locale

class AlertsActivity : AppCompatActivity() {

    private lateinit var alertListPlaceholder: LinearLayout
    private lateinit var alertEmptyLayout: LinearLayout
    private lateinit var switchAlerts: Switch
    private lateinit var clearAllBtn: Button
    private lateinit var notifPref: NotificationPref
    private lateinit var btnBackDashboard: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alerts)

        // UI
        alertListPlaceholder = findViewById(R.id.alertListPlaceholder)
        alertEmptyLayout = findViewById(R.id.alertEmptyLayout)
        switchAlerts = findViewById(R.id.switchAlerts)
        clearAllBtn = findViewById(R.id.clearAllBtn)
        btnBackDashboard = findViewById(R.id.btnBackDashboard)

        notifPref = NotificationPref(this)

        // BACK BUTTON
        btnBackDashboard.setOnClickListener { finish() }

        // Switch sync
        switchAlerts.isChecked = NotificationHelper.isNotificationsEnabled

        switchAlerts.setOnCheckedChangeListener { _, isChecked ->
            NotificationHelper.isNotificationsEnabled = isChecked
            Toast.makeText(this, if (isChecked) "Alerts Enabled" else "Alerts Disabled", Toast.LENGTH_SHORT).show()
        }

        clearAllBtn.setOnClickListener {
            notifPref.clearAll()
            loadNotifications()
            Toast.makeText(this, "All alerts cleared", Toast.LENGTH_SHORT).show()
        }

        loadNotifications()
    }

    private fun loadNotifications() {
        val alerts = notifPref.getNotifications()

        if (alerts.isEmpty()) {
            alertEmptyLayout.visibility = LinearLayout.VISIBLE
            alertListPlaceholder.visibility = LinearLayout.GONE
        } else {
            alertEmptyLayout.visibility = LinearLayout.GONE
            alertListPlaceholder.visibility = LinearLayout.VISIBLE
            displayAlerts(alerts)
        }
    }

    private fun displayAlerts(alerts: List<Pair<Long, String>>) {
        alertListPlaceholder.removeAllViews()
        val inflater = LayoutInflater.from(this)

        for (alert in alerts) {
            val view = inflater.inflate(R.layout.item_alert_card, alertListPlaceholder, false)

            val title = view.findViewById<TextView>(R.id.alertTitle)
            val desc = view.findViewById<TextView>(R.id.alertDesc)
            val source = view.findViewById<TextView>(R.id.alertSource)

            val time = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(alert.first)
            val lines = alert.second.split("\n")

            title.text = lines[0]
            desc.text = if (lines.size > 1) lines[1] else ""
            source.text = "Received: $time"

            // ⭐ Long press to delete with confirmation dialog
            view.setOnLongClickListener {
                showDeleteDialog(alert)
                true
            }

            alertListPlaceholder.addView(view)
        }
    }

    private fun showDeleteDialog(alert: Pair<Long, String>) {
        AlertDialog.Builder(this)
            .setTitle("Delete Alert")
            .setMessage("Are you sure you want to delete this alert?")
            .setPositiveButton("Yes") { _, _ ->
                notifPref.deleteNotification(alert)
                loadNotifications()
                Toast.makeText(this, "Alert deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }
}
