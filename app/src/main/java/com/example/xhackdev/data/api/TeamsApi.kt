package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.TeamsRequestResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface TeamsApi {

    @GET("/api/teams/getActiveIncomingRequests")
    suspend fun getTeamsRequests(): Response<TeamsRequestResponseDto>

    @GET("/api/teams/acceptRequestUserToTeam/{requestId}")
    suspend fun acceptRequestUserToTeam(@Path("requestId") requestId: Int): Response<Unit>

    @GET("/api/teams/declineRequestUserToTeam/{requestId}")
    suspend fun declineRequestUserToTeam(@Path("requestId") requestId: Int): Response<Unit>

    @GET("/api/teams/withdrawRequest/{requestId}")
    suspend fun withdrawRequest(@Path("requestId") requestId: Int): Response<Unit>
}