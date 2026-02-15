package com.example.salongalab1

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    
    companion object {
        private const val PREF_NAME = "AuthPrefs"
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_EMAIL = "email"
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_CREATED_AT = "created_at"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
    
    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    
    fun saveAuthToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }
    
    fun getAuthToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }
    
    fun saveUserId(userId: Long) {
        prefs.edit().putLong(KEY_USER_ID, userId).apply()
    }
    
    fun getUserId(): Long? {
        val userId = prefs.getLong(KEY_USER_ID, -1L)
        return if (userId == -1L) null else userId
    }
    
    fun saveEmail(email: String) {
        prefs.edit().putString(KEY_EMAIL, email).apply()
    }
    
    fun getEmail(): String? {
        return prefs.getString(KEY_EMAIL, null)
    }
    
    fun saveFirstName(firstName: String) {
        prefs.edit().putString(KEY_FIRST_NAME, firstName).apply()
    }
    
    fun getFirstName(): String? {
        return prefs.getString(KEY_FIRST_NAME, null)
    }
    
    fun saveLastName(lastName: String) {
        prefs.edit().putString(KEY_LAST_NAME, lastName).apply()
    }
    
    fun getLastName(): String? {
        return prefs.getString(KEY_LAST_NAME, null)
    }
    
    fun saveCreatedAt(createdAt: String) {
        prefs.edit().putString(KEY_CREATED_AT, createdAt).apply()
    }
    
    fun getCreatedAt(): String? {
        return prefs.getString(KEY_CREATED_AT, null)
    }
    
    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }
    
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    
    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
