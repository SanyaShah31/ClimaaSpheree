package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.climaaspheree.databinding.ActivityForgotPasswordBinding

import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var lottieAnim: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        lottieAnim = binding.lottieForgot
        lottieAnim.playAnimation()

        // ---------- Back Arrow ----------
        binding.ivBack.setOnClickListener {
            finish() // or navigate to LoginActivity
        }

        // ---------- Reset Button ----------
        binding.btnReset.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            if (TextUtils.isEmpty(email)) {
                showToast("Please enter your email")
            } else {
                sendPasswordReset(email)
            }
        }

        // ---------- Back to Login Text ----------
        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun sendPasswordReset(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Reset link sent to your email")
                } else {
                    showToast("Error: ${task.exception?.message}")
                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
