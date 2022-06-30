package com.example.xhackdev.di

import com.example.xhackdev.data.api.AuthApi
import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.repository.AuthRepositoryImpl
import com.example.xhackdev.data.repository.TeamsRepositoryImpl
import com.example.xhackdev.data.room.CurrentUserDao
import com.example.xhackdev.data.storage.AccessTokenStorage
import com.example.xhackdev.domain.repository.AuthRepository
import com.example.xhackdev.domain.repository.TeamsRepository
import com.example.xhackdev.domain.usecases.*
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    //-------AuthRepository
    @Provides
    fun provideAuthRepository(authApi: AuthApi, storage: AccessTokenStorage, userDao: CurrentUserDao, gson: Gson): AuthRepository{
        return AuthRepositoryImpl(authApi, storage, userDao, gson)
    }

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    fun provideRegistrationUseCase(authRepository: AuthRepository): RegistrationUseCase {
        return RegistrationUseCase(authRepository)
    }


    //-------TeamsRepository
    @Provides
    fun provideTeamsRepository(teamsApi: TeamsApi): TeamsRepository {
        return TeamsRepositoryImpl(teamsApi)
    }

    @Provides
    fun provideGetTeamsRequestsUseCase(teamsRepository: TeamsRepository): GetTeamsRequestsUseCase {
        return GetTeamsRequestsUseCase(teamsRepository)
    }

    @Provides
    fun provideDeclineRequestUserToTeamUseCasee(teamsRepository: TeamsRepository): DeclineRequestUserToTeamUseCase {
        return DeclineRequestUserToTeamUseCase(teamsRepository)
    }

    @Provides
    fun provideWithdrawRequestUseCase(teamsRepository: TeamsRepository): WithdrawRequestUseCase {
        return WithdrawRequestUseCase(teamsRepository)
    }

    @Provides
    fun provideGetTeamsDetailsRequestUseCase(teamsRepository: TeamsRepository): GetTeamsDetailsRequestUseCase {
        return GetTeamsDetailsRequestUseCase(teamsRepository)
    }

    @Provides
    fun provideGetMyTeamsRequestUseCase(teamsRepository: TeamsRepository): GetMyTeamsRequestUseCase {
        return GetMyTeamsRequestUseCase(teamsRepository)
    }

    @Provides
    fun provideSendRequestToUserUseCase(teamsRepository: TeamsRepository): SendRequestToUserUseCase {
        return SendRequestToUserUseCase(teamsRepository)
    }

    @Provides
    fun provideAcceptRequestUserToTeamUseCase(teamsRepository: TeamsRepository): AcceptRequestUserToTeamUseCase {
        return AcceptRequestUserToTeamUseCase(teamsRepository)
    }

}