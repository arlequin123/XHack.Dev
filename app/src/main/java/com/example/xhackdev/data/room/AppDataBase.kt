package com.example.xhackdev.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.xhackdev.data.room.entities.CurrentUserEntity

@Database(
    version = 1,
    entities = [
        CurrentUserEntity::class
    ]
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getCurrentUserDao(): CurrentUserDao
}