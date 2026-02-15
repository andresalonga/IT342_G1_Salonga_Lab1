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
import com.example.salongalab1.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityRegisterBinding
    private val apiService = RetrofitClient.apiService
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupInputValidation()
        setupClickListeners()
    }
    
    private fun setupInputValidation() {
        // First Name validation
        binding.etFirstName.addTextChangedListener(createTextWatcher(binding.tilFirstName) { text ->
            text.isNotEmpty()
        })
        
        // Last Name validation
        binding.etLastName.addTextChangedListener(createTextWatcher(binding.tilLastName) { text ->
            text.isNotEmpty()
        })
        
        // Email validation
        binding.etEmail.addTextChangedListener(createTextWatcher(binding.tilEmail) { text ->
            android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
        })
        
        // Password validation
        binding.etPassword.addTextChangedListener(createTextWatcher(binding.tilPassword) { text ->
            text.length >= 6
        })
        
        // Confirm Password validation
        binding.etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val confirmPassword = s.toString()
                val password = binding.etPassword.text.toString()
                
                if (confirmPassword.isEmpty()) {
                    setInputState(binding.tilConfirmPassword, null)
                } else if (confirmPassword == password && password.isNotEmpty()) {
                    setInputState(binding.tilConfirmPassword, true)
                } else {
                    setInputState(binding.tilConfirmPassword, false)
                }
            }
        })
    }
    
    private fun createTextWatcher(inputLayout: TextInputLayout, validator: (String) -> Boolean): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                setInputState(inputLayout, validator(text))
            }
        }
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
        binding.btnRegister.setOnClickListener {
            registerUser()
        }
        
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
    
    private fun registerUser() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        
        // Validation
        if (firstName.isEmpty()) {
            binding.etFirstName.error = "First name is required"
            return
        }
        
        if (lastName.isEmpty()) {
            binding.etLastName.error = "Last name is required"
            return
        }
        
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
        
        if (password.length < 6) {
            binding.etPassword.error = "Password must be at least 6 characters"
            return
        }
        
        if (password != confirmPassword) {
            binding.etConfirmPassword.error = "Passwords do not match"
            return
        }
        
        // Show loading
        showLoading(true)
        
        val user = User(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName
        )
        
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.register(user)
                }
                
                showLoading(false)
                
                if (response.isSuccessful) {
                    val authResponse = response.body()
                    Toast.makeText(
                        this@RegisterActivity,
                        authResponse?.message ?: "Registration successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                    
                    // Navigate to Login
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    finish()
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        val error = com.google.gson.Gson().fromJson(errorBody, AuthResponse::class.java)
                        error.message
                    } catch (e: Exception) {
                        "Registration failed. Please try again."
                    }
                    Toast.makeText(this@RegisterActivity, errorMessage, Toast.LENGTH_LONG).show()
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
                Toast.makeText(this@RegisterActivity, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnRegister.isEnabled = !show
    }
}
