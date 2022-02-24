package com.example.xhackdev.di

import com.example.xhackdev.domain.repository.AuthRepository
import com.example.xhackdev.domain.usecases.LoginUseCase
import com.example.xhackdev.domain.usecases.RegistrationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    fun provideRegistrationUseCase(authRepository: AuthRepository): RegistrationUseCase {
        return RegistrationUseCase(authRepository)
    }
}