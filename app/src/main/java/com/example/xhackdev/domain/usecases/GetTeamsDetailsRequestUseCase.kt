package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.models.TeamDetailsDto
import com.example.xhackdev.domain.repository.TeamsRepository
import com.example.xhackdev.utils.Result

class GetTeamsDetailsRequestUseCase(private val teamsRepository: TeamsRepository) {
    suspend fun execute(id: Int): Result<TeamDetailsDto> {
        return teamsRepository.getTeamsDetailsRequest(id)
    }
}