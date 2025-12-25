package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.climaaspheree.databinding.ActivityLoginBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private var phoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val savedEmail = prefs.getString("email", null)
        if (!savedEmail.isNullOrEmpty()) {
            binding.emailEditText.setText(savedEmail)
            binding.rememberMeCheckBox.isChecked = true
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                showToast("Please enter email and password")
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (binding.rememberMeCheckBox.isChecked) {
                            prefs.edit().putString("email", email).apply()
                        } else {
                            prefs.edit().remove("email").apply()
                        }

                        val uid = auth.currentUser?.uid
                        if (uid != null) {
                            val user = auth.currentUser
                            if (user != null && user.phoneNumber != null) {
                                // ✅ Go to Dashboard instead of Forecast
                                startActivity(Intent(this, DashboardActivity::class.java))
                                finish()
                            } else {
                                val ref = FirebaseDatabase.getInstance().getReference("Users").child(uid)
                                ref.child("phone").get().addOnSuccessListener { snapshot ->
                                    phoneNumber = snapshot.value?.toString()
                                    if (!phoneNumber.isNullOrEmpty()) {
                                        sendOTP(phoneNumber!!)
                                    } else {
                                        // ✅ Go to Dashboard instead of Forecast
                                        startActivity(Intent(this, DashboardActivity::class.java))
                                        finish()
                                    }
                                }.addOnFailureListener {
                                    showToast("Failed to fetch phone number")
                                }
                            }
                        }
                    } else {
                        showToast("Login Failed: ${task.exception?.message}")
                    }
                }
        }

        binding.forgotPasswordText.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        binding.signupRedirectText.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    private fun sendOTP(phone: String) {
        val phoneFormatted = if (phone.startsWith("+")) phone else "+$phone"

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneFormatted)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    auth.currentUser?.linkWithCredential(credential)?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // ✅ Go to Dashboard instead of Forecast
                            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                            finish()
                        } else {
                            showToast("Auto verification failed")
                        }
                    }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    showToast("OTP Failed: ${e.message}")
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    val intent = Intent(this@LoginActivity, OTPVerificationActivity::class.java)
                    intent.putExtra("verificationId", verificationId)
                    intent.putExtra("phoneNumber", phoneFormatted)
                    startActivity(intent)
                }
            }).build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
