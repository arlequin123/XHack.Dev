package com.example.xhackdev.data.models

import java.util.*

data class NewMessageDto(
    val id: Int,
    val chatId: Int,
    val chatName: String,
    val fromId: Int,
    val guid: String,
    val sender: UserDto,
    val message: String,
    val createdAt: Date
)
