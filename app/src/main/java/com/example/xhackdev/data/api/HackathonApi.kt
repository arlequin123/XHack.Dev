package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.HackBookMarkRequest
import com.example.xhackdev.data.models.HackDetailDto
import com.example.xhackdev.data.models.HackDto
import com.example.xhackdev.data.models.HackathonListRequestDto
import retrofit2.Response
import retrofit2.http.*


interface HackathonApi {

    @POST("/api/hackathons/get-list")
    suspend fun getHackathonsList(@Body request: HackathonListRequestDto): Response<List<HackDto>>

    @GET("/api/hackathons/get-by-id/{id}")
    suspend fun getHackDetail(@Path("id") id: Int): Response<HackDetailDto>

    @POST("/api/bookmarks/bookmark-hackathon")
    suspend fun addHackToBookMark(@Body request: HackBookMarkRequest)

    @POST("/api/bookmarks/remove-hackathon-bookmark")
    suspend fun removeHackToBookMark(@Body request: HackBookMarkRequest)
}