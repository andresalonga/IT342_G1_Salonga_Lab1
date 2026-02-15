package com.example.salongalab1

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("message")
    val message: String,
    
    @SerializedName("token")
    val token: String? = null,
    
    @SerializedName("email")
    val email: String? = null,
    
    @SerializedName("userId")
    val userId: Long? = null,
    
    @SerializedName("firstName")
    val firstName: String? = null,
    
    @SerializedName("lastName")
    val lastName: String? = null,
    
    @SerializedName("createdAt")
    val createdAt: String? = null
)
