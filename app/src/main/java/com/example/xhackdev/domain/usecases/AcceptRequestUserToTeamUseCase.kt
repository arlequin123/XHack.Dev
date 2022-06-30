package com.example.xhackdev.domain.usecases

import com.example.xhackdev.domain.repository.TeamsRepository
import com.example.xhackdev.utils.Result

class AcceptRequestUserToTeamUseCase(private val teamsRepository: TeamsRepository) {

    suspend fun execute(requestId: Int): Result<Unit>{
        return teamsRepository.acceptRequestUserToTeam(requestId)
    }
}