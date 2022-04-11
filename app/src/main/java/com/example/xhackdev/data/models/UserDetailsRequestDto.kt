package com.example.xhackdev.data.models

import com.example.xhackdev.data.primitives.RequestType
import com.google.gson.annotations.SerializedName

data class UserDetailsRequestDto(
    val id: Int,

    @SerializedName("user")
    val userId: Int,

    val team: UserDetailsTeamRequestDto,
    val type: RequestType
)