package com.example.xhackdev.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xhackdev.data.room.AppDataBase
import com.example.xhackdev.data.room.CurrentUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase{
        return Room.databaseBuilder(context, AppDataBase::class.java, "current_user_database").build()
    }

    @Provides
    fun provideCurrentUserDao(database: AppDataBase): CurrentUserDao {
        return database.getCurrentUserDao()
    }
}