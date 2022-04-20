package com.example.xhackdev.data.models

import com.example.xhackdev.data.primitives.TeamParticipantType

data class TeamDetailsDto(
    val id: Int,
    val name: String,
    val description: String,
    val maxCountUser: Int,
    val avatarUrl: String?,
    val captain: ShortUserDetailsDto,
    val chat: Int?,
    val isInActiveSearch: Boolean,
    val members: List<ShortUserDetailsDto>,
    val requests: List<TeamDetailsRequestDto>,
    val isBookmarked: Boolean,
    val participantType: TeamParticipantType,
)