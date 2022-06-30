package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.domain.repository.TeamsRepository
import com.example.xhackdev.utils.Result

class GetMyTeamsRequestUseCase(private val teamsRepository: TeamsRepository) {
    suspend fun execute(): Result<List<ShortTeamDetailsDto>> {
        return teamsRepository.getMyTeamsRequest()
    }
}