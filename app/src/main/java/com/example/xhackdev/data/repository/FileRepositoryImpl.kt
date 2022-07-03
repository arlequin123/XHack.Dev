package com.example.xhackdev.data.repository

import com.example.xhackdev.data.api.FileApi
import com.example.xhackdev.data.models.FileUploadResponseDto
import com.example.xhackdev.domain.repository.FileRepository
import com.example.xhackdev.utils.Result
import okhttp3.MultipartBody

class FileRepositoryImpl(private val fileApi: FileApi): FileRepository {
    override suspend fun uploadFile(image: MultipartBody.Part): Result<String> {
        val result: Result<String> = try {
            val response = fileApi.uploadFile(image)
            if(response.isSuccessful){
                response.body()?.let {
                    Result.Success(it.imageUrl)
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