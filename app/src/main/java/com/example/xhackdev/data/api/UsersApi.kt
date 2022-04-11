package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.UserDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersApi {

    @GET("/api/users/{id}")
    suspend fun getUser(@Path("id") userId: Int) : Response<UserDetailsDto>
}