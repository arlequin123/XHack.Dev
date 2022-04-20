package com.example.xhackdev.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.xhackdev.data.models.TagDto

@Entity(
    tableName = "current_user"
)
data class CurrentUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    val name: String,
    val email: String,
    val description: String,
    val specialization: String,
    val networks: List<String>,
    val tags: List<TagDto>
)