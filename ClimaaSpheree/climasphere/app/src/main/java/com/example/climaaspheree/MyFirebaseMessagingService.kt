package com.example.climaaspheree

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.climaaspheree.utils.NotificationHelper
import com.example.climaaspheree.utils.NotificationPref
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "FCM_Service"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")

        // 🚫 STOP if user disabled alerts from AlertsActivity
        if (!NotificationHelper.isNotificationsEnabled) {
            Log.d(TAG, "Notifications OFF → No alerts will show.")
            return
        }

        val pref = NotificationPref(applicationContext)

        // Notification Section (Foreground/Background Display)
        remoteMessage.notification?.let {
            val title = it.title ?: "ClimaaSpheree Alert"
            val body = it.body ?: "New weather alert received."

            // Save locally for AlertsActivity listing
            pref.saveNotification("$title\n$body")

            // Show notification to the user
            sendNotification(title, body)
        }

        // Data Payload Section (Custom Data Notifications)
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Data Payload: ${remoteMessage.data}")

            val updateType = remoteMessage.data["update_type"] ?: "Weather"
            val status = remoteMessage.data["status"] ?: "Updated"
            val title = "ClimaaSpheree $updateType Update"
            val body = "Status: $status"

            // Save locally
            pref.saveNotification("$title\n$body")

            sendNotification(title, body)
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    private fun sendNotification(title: String, messageBody: String) {

        if (!NotificationHelper.isNotificationsEnabled) return

        val intent = Intent(this, AlertsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "climaaspheree_alerts_channel"
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_bell_notification)   // VERY IMPORTANT !!
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(sound)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "ClimaaSpheree Alerts",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        manager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

    private fun sendRegistrationToServer(token: String) {
        Log.i(TAG, "Token success sent to backend (If implemented).")
    }
}
