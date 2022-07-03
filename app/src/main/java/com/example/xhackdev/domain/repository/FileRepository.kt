package com.example.xhackdev.domain.repository

import com.example.xhackdev.data.models.FileUploadResponseDto
import com.example.xhackdev.utils.Result
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Part

interface FileRepository {
    suspend fun uploadFile(image: MultipartBody.Part) : Result<String>
}