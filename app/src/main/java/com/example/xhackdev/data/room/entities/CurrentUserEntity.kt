package com.example.xhackdev.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.xhackdev.data.models.ProfileDto
import com.example.xhackdev.utils.fromJson
import com.google.gson.Gson


@Entity(
    tableName = "current_user"
)
data class CurrentUserEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    val name: String,
    val email: String,
    val description: String,
    val specialization: String,
    val networks: String,
    val tags: String
)

fun CurrentUserEntity.toProfileDto(gson: Gson) = ProfileDto(
    avatarUrl = this.avatarUrl,
    name = this.name,
    email = this.email,
    description = this.description,
    specialization = this.specialization,
    networks = gson.fromJson(this.networks),
    tags = gson.fromJson(this.tags)
)