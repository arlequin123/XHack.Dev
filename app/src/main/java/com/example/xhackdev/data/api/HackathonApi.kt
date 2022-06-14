package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.HackDto
import com.example.xhackdev.data.models.HackathonListRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface HackathonApi {

    @POST("/api/hackathons/get-list")
    suspend fun getHackathonsList(@Body request: HackathonListRequestDto): Response<List<HackDto>>
}