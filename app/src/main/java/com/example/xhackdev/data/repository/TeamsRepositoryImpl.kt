package com.example.xhackdev.data.repository

import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.models.InviteUserDto
import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.data.models.TeamDetailsDto
import com.example.xhackdev.data.models.TeamsRequestResponseDto
import com.example.xhackdev.domain.repository.TeamsRepository
import com.example.xhackdev.utils.Result

class TeamsRepositoryImpl(
    private val teamsApi: TeamsApi
): TeamsRepository {

    override suspend fun getTeamsRequests(): Result<TeamsRequestResponseDto> {

        val result: Result<TeamsRequestResponseDto> = try {
            val response = teamsApi.getTeamsRequests()
            if(response.isSuccessful){
                Result.Success(response.body())
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

    override suspend fun acceptRequestUserToTeam(requestId: Int): Result<Unit> {
        val result: Result<Unit> = try {
            val response = teamsApi.acceptRequestUserToTeam(requestId)
            if(response.isSuccessful){
                Result.Success(Unit)
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

    override suspend fun declineRequestUserToTeam(requestId: Int): Result<Unit> {
        val result: Result<Unit> = try {
            val response = teamsApi.declineRequestUserToTeam(requestId)
            if(response.isSuccessful){
                Result.Success(Unit)
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

    override suspend fun withdrawRequest(requestId: Int): Result<Unit> {
        val result: Result<Unit> = try {
            val response = teamsApi.withdrawRequest(requestId)
            if(response.isSuccessful){
                Result.Success(Unit)
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

    override suspend fun getTeamsDetailsRequest(id: Int): Result<TeamDetailsDto> {
        val result: Result<TeamDetailsDto> = try {
            val response = teamsApi.getTeamsDetailsRequest(id)
            if(response.isSuccessful){
                Result.Success(response.body())
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

    override suspend fun getMyTeamsRequest(): Result<List<ShortTeamDetailsDto>> {
        val result: Result<List<ShortTeamDetailsDto>> = try {
            val response = teamsApi.getMyTeamsRequest()
            if(response.isSuccessful){
                Result.Success(response.body())
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

    override suspend fun sendRequestToUser(request: InviteUserDto): Result<ShortTeamDetailsDto> {
        val result: Result<ShortTeamDetailsDto> = try {
            val response = teamsApi.sendRequestToUser(request)
            if(response.isSuccessful){
                Result.Success(response.body())
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }
}