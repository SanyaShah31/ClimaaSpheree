package com.example.climaaspheree

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.climaaspheree.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var lottieAnim: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        lottieAnim = binding.lottieRegistration
        lottieAnim.playAnimation()

        binding.signupButton.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (validateInput(firstName, email, phone, password)) {
                registerUser(email, password, firstName, phone)
            }
        }

        binding.backToLoginText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validateInput(firstName: String, email: String, phone: String, password: String): Boolean {
        if (TextUtils.isEmpty(firstName)) { showToast("Enter first name"); return false }
        if (TextUtils.isEmpty(email)) { showToast("Enter email"); return false }
        if (TextUtils.isEmpty(phone)) { showToast("Enter phone number"); return false }
        if (TextUtils.isEmpty(password)) { showToast("Enter password"); return false }
        if (password.length < 6) { showToast("Password must be at least 6 characters"); return false }
        return true
    }

    private fun registerUser(email: String, password: String, firstName: String, phone: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    saveUserToDatabase(user, firstName, phone)
                    showToast("Registration successful! Please login.")
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    showToast("Registration failed: ${task.exception?.message}")
                }
            }
    }

    private fun saveUserToDatabase(user: FirebaseUser?, firstName: String, phone: String) {
        val uid = user?.uid ?: return
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(uid)
        val userData = HashMap<String, String>()
        userData["firstName"] = firstName
        userData["email"] = user.email ?: ""
        userData["phone"] = phone
        ref.setValue(userData)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
