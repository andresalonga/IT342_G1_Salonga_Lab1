package com.example.salongalab1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.salongalab1.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager
    private val apiService = RetrofitClient.apiService
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        sessionManager = SessionManager(this)
        
        // Check if already logged in
        if (sessionManager.isLoggedIn()) {
            navigateToDashboard()
            return
        }
        
        setupInputValidation()
        setupClickListeners()
    }
    
    private fun setupInputValidation() {
        // Email validation on text change
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString().trim()
                if (email.isEmpty()) {
                    setInputState(binding.tilEmail, null)
                } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    setInputState(binding.tilEmail, true)
                } else {
                    setInputState(binding.tilEmail, false)
                }
            }
        })
        
        // Password validation on text change
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                if (password.isEmpty()) {
                    setInputState(binding.tilPassword, null)
                } else if (password.length >= 6) {
                    setInputState(binding.tilPassword, true)
                } else {
                    setInputState(binding.tilPassword, false)
                }
            }
        })
    }
    
    private fun setInputState(inputLayout: TextInputLayout, isValid: Boolean?) {
        when (isValid) {
            true -> {
                inputLayout.boxStrokeColor = ContextCompat.getColor(this, R.color.success)
                inputLayout.setHintTextColor(ContextCompat.getColorStateList(this, R.color.success))
            }
            false -> {
                inputLayout.boxStrokeColor = ContextCompat.getColor(this, R.color.error_color)
                inputLayout.setHintTextColor(ContextCompat.getColorStateList(this, R.color.error_color))
            }
            null -> {
                inputLayout.boxStrokeColor = ContextCompat.getColor(this, R.color.outline)
                inputLayout.setHintTextColor(ContextCompat.getColorStateList(this, R.color.text_hint))
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            loginUser()
        }
        
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        
        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun loginUser() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()
        
        // Validation
        if (email.isEmpty()) {
            binding.etEmail.error = "Email is required"
            return
        }
        
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Invalid email format"
            return
        }
        
        if (password.isEmpty()) {
            binding.etPassword.error = "Password is required"
            return
        }
        
        // Show loading
        showLoading(true)
        
        val user = User(
            email = email,
            password = password
        )
        
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.login(user)
                }
                
                showLoading(false)
                
                if (response.isSuccessful) {
                    val authResponse = response.body()
                    
                    if (authResponse != null && authResponse.token != null) {
                        // Save session
                        sessionManager.saveAuthToken(authResponse.token)
                        sessionManager.saveEmail(authResponse.email ?: email)
                        authResponse.userId?.let { sessionManager.saveUserId(it) }
                        authResponse.firstName?.let { sessionManager.saveFirstName(it) }
                        authResponse.lastName?.let { sessionManager.saveLastName(it) }
                        authResponse.createdAt?.let { sessionManager.saveCreatedAt(it) }
                        sessionManager.setLoggedIn(true)
                        
                        Toast.makeText(
                            this@LoginActivity,
                            authResponse.message ?: "Login successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        
                        navigateToDashboard()
                    } else {
                        Toast.makeText(this@LoginActivity, "Invalid response from server", Toast.LENGTH_LONG).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        val error = com.google.gson.Gson().fromJson(errorBody, AuthResponse::class.java)
                        error.message
                    } catch (e: Exception) {
                        "Login failed. Invalid email or password."
                    }
                    Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                showLoading(false)
                val errorMessage = when {
                    e.message?.contains("Unable to resolve host") == true -> 
                        "Cannot connect to server. Make sure the backend is running."
                    e.message?.contains("Connection refused") == true ->
                        "Connection refused. Is the backend running on port 8080?"
                    else -> "Error: ${e.message}"
                }
                Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
    
    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnLogin.isEnabled = !show
    }
}
