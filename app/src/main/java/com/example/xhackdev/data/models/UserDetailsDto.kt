package com.example.xhackdev.data.models

import com.example.xhackdev.data.primitives.TeamParticipantType

data class UserDetailsDto(
    val avatarUrl: String?,
    val chatId: Int?,
    val description: String,
    val email: String,
    val id: Int,
    val isAvailableForSearching: Boolean,
    val isBookmarked: Boolean,
    val name: String,
    val networks: List<String>,
    val participantType: TeamParticipantType,
    val requests: List<UserDetailsRequestDto>,
    val specialization: String,
    val tags: List<TagDto>
)