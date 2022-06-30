package com.example.xhackdev.data.models

data class SendMessageDto(
    val chatId: Int? = null,
    val message: String,
    val teamId: Int? = null,
    val secondUserId: Int? = null,
    val guid: String
)
