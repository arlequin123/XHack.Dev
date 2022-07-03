package com.example.xhackdev.domain.usecasesGetHackDetailUseCase

import com.example.xhackdev.data.models.HackDetailDto
import com.example.xhackdev.data.models.UserBookmarkRequest
import com.example.xhackdev.domain.repository.HackathonRepository
import com.example.xhackdev.utils.Result

class GetHackDetailUseCase(private val hackathonRepository: HackathonRepository) {
    suspend fun execute(id: Int): Result<HackDetailDto> {
        return hackathonRepository.getHackDetail(id)
    }
}