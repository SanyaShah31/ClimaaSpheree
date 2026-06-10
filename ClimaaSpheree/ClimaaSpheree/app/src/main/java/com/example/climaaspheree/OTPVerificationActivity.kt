package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.climaaspheree.databinding.ActivityOtpVerificationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OTPVerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpVerificationBinding
    private lateinit var auth: FirebaseAuth
    private var verificationId: String? = null

    private var resendEnabled = false
    private var secondsLeft = 60
    private val RESEND_TIMER: Long = 60000 // 60 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        verificationId = intent.getStringExtra("verificationId")

        if (verificationId == null) {
            showToast("OTP session expired. Please login again.")
            finish()
            return
        }

        // --- Verify OTP ---
        binding.btnVerify.setOnClickListener {
            val code = binding.etOTP.text.toString().trim()
            if (code.isEmpty()) {
                showToast("Enter OTP")
            } else {
                verifyOTP(code)
            }
        }

        // --- Start Resend OTP Timer ---
        startResendOTPTimer()

        binding.tvResendOTP.setOnClickListener {
            if (resendEnabled) {
                showToast("OTP Resent")
                startResendOTPTimer()
                // Add resend logic if needed
            } else {
                showToast("Please wait for timer to finish")
            }
        }
    }

    private fun verifyOTP(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)

        val currentUser = auth.currentUser
        if (currentUser != null) {
            //  Link phone verification with logged-in user
            currentUser.linkWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
//                        showToast("Phone number verified successfully!")
                        showToast("Login successfully!")

                        //  Navigate safely to ForecastActivity
                        val intent = Intent(this@OTPVerificationActivity, ForecastActivity::class.java)
                        startActivity(intent)

                        //  Do NOT clear all activities here — only finish OTP screen
                        finish()
                    } else {
                        showToast("Verification failed: ${task.exception?.message}")
                    }
                }
        } else {
            // If somehow user is not logged in, sign in using the phone
            auth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Login Successful")

                    val intent = Intent(this@OTPVerificationActivity, ForecastActivity::class.java)
                    startActivity(intent)

                    //  Only finish OTP screen, not the entire task
                    finish()
                } else {
                    showToast("Invalid OTP: ${task.exception?.message}")
                }
            }
        }
    }


    private fun startResendOTPTimer() {
        resendEnabled = false
        secondsLeft = 60

        object : CountDownTimer(RESEND_TIMER, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsLeft = (millisUntilFinished / 1000).toInt()
                binding.tvResendOTP.text =
                    "Resend OTP in 00:${if (secondsLeft < 10) "0$secondsLeft" else secondsLeft}"
            }

            override fun onFinish() {
                resendEnabled = true
                binding.tvResendOTP.text = "Resend OTP"
            }
        }.start()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
