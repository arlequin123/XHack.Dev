package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.TeamsRequestResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface TeamsApi {

    @GET("./api/teams/getActiveIncomingRequests")
    suspend fun getTeamsRequests(): Response<TeamsRequestResponseDto>
}