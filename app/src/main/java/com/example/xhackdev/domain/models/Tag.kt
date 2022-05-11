package com.example.xhackdev.domain.models

import com.example.xhackdev.data.models.TagDto

data class Tag(
    val name: String,
    val id: Int,
    var isSelected: Boolean
){
    constructor(dto: TagDto, isSelected: Boolean): this(
        dto.name,
        dto.id,
        isSelected
    )
}
