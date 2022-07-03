package com.example.xhackdev.domain.repository

import com.example.xhackdev.data.models.HackDetailDto
import com.example.xhackdev.data.models.HackDto
import com.example.xhackdev.data.models.HackathonListRequestDto
import com.example.xhackdev.utils.Result
import retrofit2.http.Body
import retrofit2.http.Path

interface HackathonRepository {
    suspend fun getHackDetail( id: Int): Result<HackDetailDto>
}