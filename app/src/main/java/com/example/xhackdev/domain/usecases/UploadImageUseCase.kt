package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.models.TeamBookmarkRequest
import com.example.xhackdev.data.repository.FileRepositoryImpl
import com.example.xhackdev.domain.repository.FileRepository
import com.example.xhackdev.utils.Result
import okhttp3.MultipartBody

class UploadImageUseCase(private val fileRepository: FileRepository) {
    suspend fun execute(image: MultipartBody.Part): Result<String> {
        return fileRepository.uploadFile(image)
    }
}