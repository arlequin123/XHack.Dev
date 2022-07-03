package com.example.xhackdev.data.repository

import com.example.xhackdev.data.api.HackathonApi
import com.example.xhackdev.data.models.HackDetailDto
import com.example.xhackdev.data.models.HackDto
import com.example.xhackdev.data.models.HackathonListRequestDto
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.domain.repository.HackathonRepository
import com.example.xhackdev.utils.Result

class HackathonRepositoryImpl(private val hackApi: HackathonApi): HackathonRepository {
    override suspend fun getHackDetail(id: Int): Result<HackDetailDto> {
        val result: Result<HackDetailDto> = try {
            val response = hackApi.getHackDetail(id)
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