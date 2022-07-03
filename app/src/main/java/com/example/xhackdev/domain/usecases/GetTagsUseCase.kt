package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.domain.repository.TagsRepository
import com.example.xhackdev.utils.Result
import okhttp3.MultipartBody

class GetTagsUseCase(private val tagsRepository: TagsRepository) {
    suspend fun execute(): Result<List<TagDto>> {
        return tagsRepository.getTags()
    }
}