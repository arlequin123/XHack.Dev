package com.example.xhackdev.di

import com.example.xhackdev.data.api.*
import com.example.xhackdev.data.repository.*
import com.example.xhackdev.data.room.CurrentUserDao
import com.example.xhackdev.data.storage.AccessTokenStorage
import com.example.xhackdev.domain.interfaces.HackRemoteDataSource
import com.example.xhackdev.domain.repository.*
import com.example.xhackdev.domain.usecases.*
import com.example.xhackdev.domain.usecasesGetHackDetailUseCase.GetHackDetailUseCase
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


    //BookmarkRepository
    @Provides
    @Singleton
    fun provideBookMarkRepository(bookmarkApi: BookmarkApi): BookmarkRepository {
        return BookmarkRepositoryImpl(bookmarkApi)
    }

    @Provides
    @Singleton
    fun provideAddUserToBookmarkUseCase(bookmarkRepository: BookmarkRepository): AddUserToBookmarkUseCase {
        return AddUserToBookmarkUseCase(bookmarkRepository)
    }

    @Provides
    @Singleton
    fun provideRemoveUserFromBookmarkUseCase(bookmarkRepository: BookmarkRepository): RemoveUserFromBookmarkUseCase {
        return RemoveUserFromBookmarkUseCase(bookmarkRepository)
    }

    @Provides
    @Singleton
    fun provideRemoveTeamFromBookmarkUseCase(bookmarkRepository: BookmarkRepository): RemoveTeamFromBookmarkUseCase {
        return RemoveTeamFromBookmarkUseCase(bookmarkRepository)
    }

    @Provides
    @Singleton
    fun provideAddTeamToBookmarkUseCase(bookmarkRepository: BookmarkRepository): AddTeamToBookmarkUseCase {
        return AddTeamToBookmarkUseCase(bookmarkRepository)
    }


    //FileRepository
    @Provides
    @Singleton
    fun provideFileRepository(fileApi: FileApi): FileRepository {
        return FileRepositoryImpl(fileApi)
    }

    @Provides
    @Singleton
    fun provideUploadImageUseCase(fileRepository: FileRepository): UploadImageUseCase {
        return UploadImageUseCase(fileRepository)
    }


    //TagRepository
    @Provides
    @Singleton
    fun provideTagsRepository(tagsApi: TagsApi): TagsRepository {
        return TagsRepositoryImpl(tagsApi)
    }

    @Provides
    @Singleton
    fun provideGetTagsUseCase(tagsRepository: TagsRepository): GetTagsUseCase {
        return GetTagsUseCase(tagsRepository)
    }


    //HackathonRepository
    @Provides
    @Singleton
    fun provideHackathonRepository(hackathonApi: HackathonApi): HackathonRepository {
        return HackathonRepositoryImpl(hackathonApi)
    }

    @Provides
    @Singleton
    fun provideGetHackDetailUseCase(hackRepository: HackathonRepository): GetHackDetailUseCase {
        return GetHackDetailUseCase(hackRepository)
    }


    @Provides
    @Singleton
    fun provideHackRemoteDataSource(hackathonApi: HackathonApi): HackRemoteDataSource {
        return HackRemoteDataSourceImpl(hackathonApi)
    }
}