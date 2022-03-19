package com.example.xhackdev.data.models

import com.example.xhackdev.data.primitives.RequestType

data class RequestDto(
    val id: Int,
    val user: UserDto,
    val team: TeamDto,
    val type: RequestType,
    val isCanceled: Boolean
)
