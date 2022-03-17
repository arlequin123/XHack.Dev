package com.example.xhackdev.data.models

data class TeamDto(
    val id: Int,
    val name: String,
    val description: String?,
    val maxCountUser: Int,
    val avatarUrl: String?,
    val captainId: Int
)
