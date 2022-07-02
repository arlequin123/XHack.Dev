package com.example.xhackdev.di

import com.example.xhackdev.data.api.AuthApi
import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.api.UsersApi
import com.example.xhackdev.data.repository.AuthRepositoryImpl
import com.example.xhackdev.data.repository.TeamsRepositoryImpl
import com.example.xhackdev.data.repository.UserRepositoryImpl
import com.example.xhackdev.data.room.CurrentUserDao
import com.example.xhackdev.data.storage.AccessTokenStorage
import com.example.xhackdev.domain.repository.AuthRepository
import com.example.xhackdev.domain.repository.TeamsRepository
import com.example.xhackdev.domain.repository.UserRepository
import com.example.xhackdev.domain.usecases.*
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    //-------AuthRepository
    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi, storage: AccessTokenStorage, userDao: CurrentUserDao, gson: Gson): AuthRepository{
        return AuthRepositoryImpl(authApi, storage, userDao, gson)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideRegistrationUseCase(authRepository: AuthRepository): RegistrationUseCase {
        return RegistrationUseCase(authRepository)
    }


    //-------TeamsRepository
    @Provides
    @Singleton
    fun provideTeamsRepository(teamsApi: TeamsApi): TeamsRepository {
        return TeamsRepositoryImpl(teamsApi)
    }

    @Provides
    @Singleton
    fun provideGetTeamsRequestsUseCase(teamsRepository: TeamsRepository): GetTeamsRequestsUseCase {
        return GetTeamsRequestsUseCase(teamsRepository)
    }

    @Provides
    @Singleton
    fun provideDeclineRequestUserToTeamUseCase(teamsRepository: TeamsRepository): DeclineRequestUserToTeamUseCase {
        return DeclineRequestUserToTeamUseCase(teamsRepository)
    }

    @Provides
    @Singleton
    fun provideWithdrawRequestUseCase(teamsRepository: TeamsRepository): WithdrawRequestUseCase {
        return WithdrawRequestUseCase(teamsRepository)
    }

    @Provides
    @Singleton
    fun provideGetTeamsDetailsRequestUseCase(teamsRepository: TeamsRepository): GetTeamsDetailsRequestUseCase {
        return GetTeamsDetailsRequestUseCase(teamsRepository)
    }

    @Provides
    @Singleton
    fun provideGetMyTeamsRequestUseCase(teamsRepository: TeamsRepository): GetMyTeamsRequestUseCase {
        return GetMyTeamsRequestUseCase(teamsRepository)
    }

    @Provides
    @Singleton
    fun provideSendRequestToUserUseCase(teamsRepository: TeamsRepository): SendRequestToUserUseCase {
        return SendRequestToUserUseCase(teamsRepository)
    }

    @Provides
    @Singleton
    fun provideAcceptRequestUserToTeamUseCase(teamsRepository: TeamsRepository): AcceptRequestUserToTeamUseCase {
        return AcceptRequestUserToTeamUseCase(teamsRepository)
    }


    //UsersRepository
    @Provides
    @Singleton
    fun provideUserRepository(usersApi: UsersApi): UserRepository {
        return UserRepositoryImpl(usersApi)
    }

    @Provides
    @Singleton
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetProfileUseCase(userRepository: UserRepository, userDao: CurrentUserDao, gson: Gson): GetProfileUseCase {
        return GetProfileUseCase(userRepository, userDao, gson)
    }

    @Provides
    @Singleton
    fun provideUpdateProfileUseCase(userRepository: UserRepository): UpdateProfileUseCase {
        return UpdateProfileUseCase(userRepository)
    }
}