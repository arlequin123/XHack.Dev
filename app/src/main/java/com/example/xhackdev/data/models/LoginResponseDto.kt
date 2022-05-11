package com.example.xhackdev.data.models

data class LoginResponseDto(
    val token: String,
    val user: UserDto
)