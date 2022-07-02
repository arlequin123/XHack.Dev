package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.models.InviteUserDto
import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.data.models.UserDetailsDto
import com.example.xhackdev.domain.repository.UserRepository
import com.example.xhackdev.utils.Result

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend fun execute(userId: Int): Result<UserDetailsDto> {
        return userRepository.getUser(userId)
    }
}