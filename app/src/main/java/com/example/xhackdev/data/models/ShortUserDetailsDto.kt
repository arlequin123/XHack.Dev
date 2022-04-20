package com.example.xhackdev.data.models

data class ShortUserDetailsDto(
    val id: Int,
    val name: String,
    val avatarUrl: String?,
    val networks: List<String>,
    val specialization: String,
    val email: String,
    val description: String,
)
