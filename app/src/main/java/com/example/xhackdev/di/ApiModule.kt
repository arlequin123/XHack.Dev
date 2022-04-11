package com.example.xhackdev.di

import android.content.Context
import com.example.xhackdev.data.api.AuthApi
import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.api.UsersApi
import com.example.xhackdev.data.sharedprefs.SharedPrefsAccessToken
import com.example.xhackdev.data.storage.AccessTokenStorage
import com.example.xhackdev.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoggingInterceptor
    }


    @Provides
    @Singleton
    fun provideHttpClient(httpLoggingInterceptor: Interceptor, tokenStorage: AccessTokenStorage): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(Interceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${tokenStorage.getAccessToken()}")
                    .addHeader("Content-Type", "application/json")
                    .build()
                it.proceed(newRequest)
            })
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }


    @Provides
    @Singleton
    fun provideTeamsApi(retrofit: Retrofit): TeamsApi {
        return retrofit.create(TeamsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersApi(retrofit: Retrofit): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }
}