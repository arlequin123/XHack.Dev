package com.example.xhackdev.data.repository

import com.example.xhackdev.data.api.TagsApi
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.domain.repository.TagsRepository
import com.example.xhackdev.utils.Result

class TagsRepositoryImpl(private val tagApi: TagsApi): TagsRepository {
    override suspend fun getTags(): Result<List<TagDto>> {
        val result: Result<List<TagDto>> = try {
            val response = tagApi.getTags()
            if(response.isSuccessful){
                response.body()?.let {
                    Result.Success(it)
                }
                Result.Error("Произошла ошибка")
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }
}