package com.example.xhackdev.data.models

import java.util.*

data class HackDto(
    val id: Int,
    val name: String,
    val description: String,
    val isOnline: Boolean,
    val location: String,
    val startDate: Date,
    val endDate: Date,
    val siteUrl: String,
    val avatarUrl: String,
    val tags: List<TagDto>
    )
