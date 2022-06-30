package com.example.xhackdev.domain.usecases

import com.example.xhackdev.domain.repository.AuthRepository
import com.example.xhackdev.utils.Result

class RegistrationUseCase(private val authRepository: AuthRepository) {

    suspend fun execute(email:String, password:String, name: String): Result<Unit>{
        return authRepository.registration(email, password, name)
    }
}