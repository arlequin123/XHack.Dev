package com.example.xhackdev.data.models

import com.example.xhackdev.data.primitives.RequestType
import com.google.gson.annotations.SerializedName

data class TeamDetailsRequestDto(
    val id: Int,

    @SerializedName("user")
    val userId: Int,

    @SerializedName("team")
    val teamId: Int,

    val type: RequestType
)
