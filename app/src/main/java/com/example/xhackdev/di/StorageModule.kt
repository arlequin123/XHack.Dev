package com.example.xhackdev.di

import android.content.Context
import com.example.xhackdev.data.sharedprefs.SharedPrefsAccessToken
import com.example.xhackdev.data.storage.AccessTokenStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    fun provideAccessTokenStorage(@ApplicationContext context: Context): AccessTokenStorage {
        return SharedPrefsAccessToken(context = context)
    }
}