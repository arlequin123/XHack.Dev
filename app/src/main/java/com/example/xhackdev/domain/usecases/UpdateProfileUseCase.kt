package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.models.ProfileDto
import com.example.xhackdev.domain.repository.UserRepository
import com.example.xhackdev.utils.Result

class UpdateProfileUseCase(private val userRepository: UserRepository) {
    suspend fun execute(profile: ProfileDto): Result<Unit> {
        return userRepository.updateProfile(profile)
    }
}