package com.example.salongalab1

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    
    @POST("api/auth/register")
    suspend fun register(@Body user: User): Response<AuthResponse>
    
    @POST("api/auth/login")
    suspend fun login(@Body user: User): Response<AuthResponse>
}
