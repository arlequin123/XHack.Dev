package com.example.xhackdev.domain.models

import com.example.xhackdev.data.models.TeamDto
import com.example.xhackdev.data.models.UserDto
import com.example.xhackdev.data.primitives.RequestType

data class RequestItem(
    val id: Int,
    val user: UserDto,
    val team: TeamDto,
    val type: RequestType,
    val isCanceled: Boolean
)