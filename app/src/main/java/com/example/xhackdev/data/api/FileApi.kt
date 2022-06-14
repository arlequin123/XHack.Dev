package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.FileUploadResponseDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileApi {

    @Multipart
    @POST("/api/file-upload/single")
    suspend fun uploadFile(@Part image: MultipartBody.Part) : Response<FileUploadResponseDto>
}