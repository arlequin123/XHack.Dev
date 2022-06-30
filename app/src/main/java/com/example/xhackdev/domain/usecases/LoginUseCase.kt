package com.example.xhackdev.domain.usecases

import com.example.xhackdev.domain.repository.AuthRepository
import com.example.xhackdev.utils.Result

class LoginUseCase(private val authRepository: AuthRepository) {

    suspend fun execute(email:String, password:String): Result<Unit>{
        return authRepository.login(email, password)
    }
}