package com.example.xhackdev.data.models

import com.google.gson.annotations.SerializedName

data class UserDetailsTeamRequestDto(
    val id: Int,
    val name: String,
    val description: String?,
    val maxCountUser: Int,
    val avatarUrl: String?,

    @SerializedName("captain")
    val captainId: Int,
)