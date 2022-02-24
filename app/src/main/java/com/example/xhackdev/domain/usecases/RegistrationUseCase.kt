package com.example.xhackdev.domain.usecases

import com.example.xhackdev.domain.repository.AuthRepository

class RegistrationUseCase(private val authRepository: AuthRepository) {

    fun execute(email:String, password:String){
        authRepository.login(email, password)
    }
}