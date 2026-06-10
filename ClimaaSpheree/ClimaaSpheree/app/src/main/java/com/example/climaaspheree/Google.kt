package com.example.climaaspheree

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.climaaspheree.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
//import com.google.android.gms.ads.MobileAds   // ✅ Added

class Google : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private val TAG = "ClimaaSpheree"

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.d(TAG, "✅ Notification permission granted.")
        } else {
            Toast.makeText(
                this,
                "⚠ Notifications disabled.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google)

        // ✅ Initialize Firebase Analytics
        firebaseAnalytics = Firebase.analytics

        // ✅ Initialize Mobile Ads (No UI change, no ad shown)
//        MobileAds.initialize(this) {}   // ← Only this added

        askNotificationPermission()
        getFCMToken()

        trackScreenView("Main Screen")
        onUserRegistered("Email")
        onTutorialCompleted()

        setupUI()
    }

    private fun setupUI() {
        val btnEvent = findViewById<Button?>(R.id.btnTrackEvent)
        val btnToken = findViewById<Button?>(R.id.btnGetToken)
        val tvToken = findViewById<TextView?>(R.id.tvToken)

        btnEvent?.setOnClickListener {
            onTutorialCompleted()
            Toast.makeText(this, "🎯 Tutorial event logged!", Toast.LENGTH_SHORT).show()
        }

        btnToken?.setOnClickListener {
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(this, "❌ Failed to fetch token", Toast.LENGTH_SHORT).show()
                    return@addOnCompleteListener
                }
                val token = task.result
                tvToken?.text = token
                Log.d(TAG, "🔥 FCM Token: $token")
            }
        }
    }

    private fun trackScreenView(screenName: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "GoogleDemo")
        }
    }

    private fun onTutorialCompleted() {
        firebaseAnalytics.logEvent("tutorial_complete") {
            param("progress_level", 5)
            param("time_spent_minutes", 12.5)
        }
    }

    private fun onUserRegistered(method: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP) {
            param(FirebaseAnalytics.Param.METHOD, method)
        }
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "❌ FCM token fetch failed", task.exception)
                return@addOnCompleteListener
            }
            val token = task.result
            Log.d(TAG, "✅ FCM Token: $token")
            Toast.makeText(this, "Token: ${token.take(10)}...", Toast.LENGTH_LONG).show()
        }
    }

    private fun askNotificationPermission() {
        val permission = Manifest.permission.POST_NOTIFICATIONS
        if (ContextCompat.checkSelfPermission(this, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(permission)
        }
    }
}
