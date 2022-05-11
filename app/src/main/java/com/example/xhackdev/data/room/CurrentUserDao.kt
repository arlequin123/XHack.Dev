package com.example.xhackdev.data.room

import androidx.room.*
import com.example.xhackdev.data.room.entities.CurrentUserEntity

@Dao
interface  CurrentUserDao {

    @Query("SELECT * FROM current_user LIMIT 1")
    suspend fun getCurrentUser(): CurrentUserEntity

    @Update
    suspend fun updateUser(currentUse: CurrentUserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(currentUse: CurrentUserEntity)

    @Delete
    suspend fun deleteUser(currentUse: CurrentUserEntity)
}