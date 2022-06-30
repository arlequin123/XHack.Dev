package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.models.InviteUserDto
import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.domain.repository.TeamsRepository
import com.example.xhackdev.utils.Result

class SendRequestToUserUseCase(private val teamsRepository: TeamsRepository) {
    suspend fun execute(request: InviteUserDto): Result<ShortTeamDetailsDto> {
        return teamsRepository.sendRequestToUser(request)
    }
}