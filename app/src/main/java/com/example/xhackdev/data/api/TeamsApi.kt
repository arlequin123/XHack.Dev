package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.InviteUserDto
import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.data.models.TeamDetailsDto
import com.example.xhackdev.data.models.TeamsRequestResponseDto
import retrofit2.Response
import retrofit2.http.*

interface TeamsApi {

    @GET("/api/teams/getActiveIncomingRequests")
    suspend fun getTeamsRequests(): Response<TeamsRequestResponseDto>

    @GET("/api/teams/acceptRequestUserToTeam/{requestId}")
    suspend fun acceptRequestUserToTeam(@Path("requestId") requestId: Int): Response<Unit>

    @GET("/api/teams/declineRequestUserToTeam/{requestId}")
    suspend fun declineRequestUserToTeam(@Path("requestId") requestId: Int): Response<Unit>

    @GET("/api/teams/withdrawRequest/{requestId}")
    suspend fun withdrawRequest(@Path("requestId") requestId: Int): Response<Unit>

    @GET("/api/teams/getDetails/{id}")
    suspend fun getTeamsDetailsRequest(@Path("id") id: Int): Response<TeamDetailsDto>

    @GET("/api/teams/get-my-teams")
    suspend fun getMyTeamsRequest(): Response<List<ShortTeamDetailsDto>>

    @POST("/api/teams/send-request-to-user")
    suspend fun sendRequestToUser(@Body request: InviteUserDto): Response<ShortTeamDetailsDto>
}