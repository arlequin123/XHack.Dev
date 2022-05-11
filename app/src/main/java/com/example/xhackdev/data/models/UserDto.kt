package com.example.xhackdev.data.models

data class UserDto(
    val id: Int,
    val name: String,
    val avatarUrl: String?,
    val isAvailableForSearching: Boolean,
    val specialization: String,
    val email: String,
    val description: String,
    val networks: List<String>
)
