package com.example.xhackdev.domain.models

import com.example.xhackdev.data.models.HackDto
import com.example.xhackdev.data.models.TagDto
import java.util.*

class Hack(dto: HackDto) {
    val id: Int = dto.id
    val name: String =dto.name
    val description: String = dto.description
    val isOnline: Boolean = dto.isOnline
    val location: String = dto.location
    val startDate: Date = dto.startDate
    val endDate: Date = dto.endDate
    val siteUrl: String = dto.siteUrl
    val avatarUrl: String = dto.avatarUrl
    val tags: List<TagDto> = dto.tags
}