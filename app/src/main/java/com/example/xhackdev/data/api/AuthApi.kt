package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.LoginRequestDto
import com.example.xhackdev.data.models.LoginResponseDto
import com.example.xhackdev.data.models.RegisterRequestDto
import com.example.xhackdev.data.models.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequestDto): Response<LoginResponseDto>

    @POST("/api/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequestDto): Response<RegisterResponseDto>
}