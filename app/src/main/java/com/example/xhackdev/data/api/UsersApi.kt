package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.ProfileDto
import com.example.xhackdev.data.models.UserDetailsDto
import retrofit2.Response
import retrofit2.http.*

interface UsersApi {

    @GET("/api/users/{id}")
    suspend fun getUser(@Path("id") userId: Int) : Response<UserDetailsDto>

    @GET("/api/users/profile")
    suspend fun getProfile() : Response<ProfileDto>

    @PATCH("/api/users/profile")
    suspend fun updateProfile(@Body profile: Int) : Response<Unit>
}