package com.example.salongalab1

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Long? = null,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("password")
    val password: String,
    
    @SerializedName("firstName")
    val firstName: String? = null,
    
    @SerializedName("lastName")
    val lastName: String? = null,
    
    @SerializedName("createdAt")
    val createdAt: String? = null
)
