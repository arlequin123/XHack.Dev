package com.example.xhackdev.data.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.xhackdev.data.room.entities.CurrentUserEntity

@Dao
interface  CurrentUserDao {

    @Query("SELECT * FROM current_user LIMIT 1")
    suspend fun getCurrentUser(): CurrentUserEntity

    @Update
    suspend fun updateUser(currentUse: CurrentUserEntity)
}