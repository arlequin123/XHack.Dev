package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.TagDto
import retrofit2.Response
import retrofit2.http.GET

interface TagsApi {

    @GET("/api/tags/getTagsList")
    suspend fun getTags() : Response<List<TagDto>>
}