package com.example.xhackdev.domain.repository

import com.example.xhackdev.data.models.ProfileDto
import com.example.xhackdev.data.models.UserDetailsDto
import com.example.xhackdev.utils.Result
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path

interface UserRepository {
    suspend fun getUser(userId: Int): Result<UserDetailsDto>

    suspend fun getProfile() : Result<ProfileDto>

    suspend fun updateProfile(profile: ProfileDto) : Result<Unit>
}