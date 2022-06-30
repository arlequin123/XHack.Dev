package com.example.xhackdev.domain.repository

import com.example.xhackdev.data.models.InviteUserDto
import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.data.models.TeamDetailsDto
import com.example.xhackdev.data.models.TeamsRequestResponseDto
import com.example.xhackdev.utils.Result

interface TeamsRepository {
    suspend fun getTeamsRequests(): Result<TeamsRequestResponseDto>

    suspend fun acceptRequestUserToTeam(requestId: Int): Result<Unit>

    suspend fun declineRequestUserToTeam(requestId: Int): Result<Unit>

    suspend fun withdrawRequest( requestId: Int): Result<Unit>

    suspend fun getTeamsDetailsRequest(id: Int): Result<TeamDetailsDto>

    suspend fun getMyTeamsRequest(): Result<List<ShortTeamDetailsDto>>

    suspend fun sendRequestToUser(request: InviteUserDto): Result<ShortTeamDetailsDto>
}