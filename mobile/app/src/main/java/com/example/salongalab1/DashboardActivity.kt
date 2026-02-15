package com.example.salongalab1

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.salongalab1.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var sessionManager: SessionManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        sessionManager = SessionManager(this)
        
        // Check if logged in
        if (!sessionManager.isLoggedIn()) {
            navigateToLogin()
            return
        }
        
        // Setup toolbar
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_logout -> {
                    showLogoutConfirmation()
                    true
                }
                else -> false
            }
        }
        
        loadUserData()
        setupClickListeners()
    }
    
    private fun loadUserData() {
        val firstName = sessionManager.getFirstName() ?: ""
        val lastName = sessionManager.getLastName() ?: ""
        val email = sessionManager.getEmail() ?: ""
        val userId = sessionManager.getUserId()
        val createdAt = sessionManager.getCreatedAt()
        
        // Set user data
        binding.tvName.text = "$firstName $lastName".trim()
        binding.tvEmail.text = email
        binding.tvUserId.text = userId?.toString() ?: "N/A"
        binding.tvCreatedAt.text = createdAt ?: "N/A"
        
        // Welcome message
        if (firstName.isNotEmpty()) {
            binding.tvWelcome.text = "Welcome, $firstName!"
        }
    }
    
    private fun setupClickListeners() {
        binding.btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }
    
    private fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Sign Out")
            .setMessage("Are you sure you want to sign out?")
            .setPositiveButton("Sign Out") { _, _ ->
                logout()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun logout() {
        // Clear session
        sessionManager.clearSession()
        
        Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show()
        
        // Navigate to Login
        navigateToLogin()
    }
    
    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
