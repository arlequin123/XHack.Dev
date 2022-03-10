package com.example.xhackdev.di

import android.content.Context
import com.example.xhackdev.data.api.XHackApi
import com.example.xhackdev.data.sharedprefs.SharedPrefsAccessToken
import com.example.xhackdev.data.storage.AccessTokenStorage
import com.example.xhackdev.domain.repository.AuthRepository
import com.example.xhackdev.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

//    @Provides
//    fun provideAuthRepository(api: XHackApi): AuthRepository {
//        return AuthRepositoryImpl(api)
//    }

    @Provides
    fun provideAccessTokenStorage(@ApplicationContext context: Context): AccessTokenStorage {
        return SharedPrefsAccessToken(context = context)
    }
}