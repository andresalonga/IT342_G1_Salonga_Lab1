package com.example.salongalab1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Check if user is logged in
        val sessionManager = SessionManager(this)
        if (sessionManager.isLoggedIn()) {
            // User is logged in, go to dashboard
            startActivity(Intent(this, DashboardActivity::class.java))
        } else {
            // User is not logged in, go to login
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}