package com.example.xhackdev.domain.repository

import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.utils.Result
import retrofit2.Response

interface TagsRepository {
    suspend fun getTags() : Result<List<TagDto>>
}