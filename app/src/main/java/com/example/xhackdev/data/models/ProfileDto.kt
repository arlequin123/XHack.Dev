package com.example.xhackdev.data.models

data class ProfileDto(
    val avatarUrl: String?,
    val name: String,
    val email: String,
    val description: String,
    val specialization: String,
    val networks: List<String>,
    val tags: List<TagDto>
)