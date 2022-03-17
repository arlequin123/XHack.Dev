package com.example.xhackdev.data.models

data class RequestDto(
    val id: Int,
    val user: UserDto,
    val team: TeamDto,
    val type: String,
    val isCanceled: Boolean
)
