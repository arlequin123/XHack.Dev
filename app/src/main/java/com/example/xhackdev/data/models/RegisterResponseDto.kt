package com.example.xhackdev.data.models

data class RegisterResponseDto(
    val token: String,
    val name: String,
    val email: String,
    val avatarUrl: String?,
    val id: Int,
    val networks: List<String>,
    val specialization: String,
    val description: String
)