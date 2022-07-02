package com.example.xhackdev.data.repository

import com.example.xhackdev.data.api.UsersApi
import com.example.xhackdev.data.models.ProfileDto
import com.example.xhackdev.data.models.TeamsRequestResponseDto
import com.example.xhackdev.data.models.UserDetailsDto
import com.example.xhackdev.domain.repository.UserRepository
import com.example.xhackdev.utils.Result

class UserRepositoryImpl(private val userApi: UsersApi): UserRepository {
    override suspend fun getUser(userId: Int): Result<UserDetailsDto> {
        val result: Result<UserDetailsDto> = try {
            val response = userApi.getUser(userId)
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

    override suspend fun getProfile(): Result<ProfileDto> {
        val result: Result<ProfileDto> = try {
            val response = userApi.getProfile()
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

    override suspend fun updateProfile(profile: ProfileDto): Result<Unit> {
        val result: Result<Unit> = try {
            val response = userApi.updateProfile(profile)
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
}