package com.example.xhackdev.data.models

data class HackathonListRequestDto(
    val take: Int,
    val page: Int,
    val filter: String = ""
    )
