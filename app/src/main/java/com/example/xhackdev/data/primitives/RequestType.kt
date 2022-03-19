package com.example.xhackdev.data.primitives

import com.google.gson.annotations.SerializedName

enum class RequestType {
    None,

    @SerializedName("UserToTeam")
    UserToTeam,

    @SerializedName("TeamToUser")
    TeamToUser,
}