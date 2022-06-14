package com.example.xhackdev.domain.models

import com.example.xhackdev.data.models.ProfileDto
import com.example.xhackdev.data.models.ShortUserDetailsDto
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.data.room.entities.CurrentUserEntity

class ProfileModel(
    var avatarUrl: String?,
    var name: String,
    var email: String,
    var description: String,
    var specialization: String,
    var networks: ArrayList<Network>,
    var tags: ArrayList<Tag>,
){
    constructor(profileDto: ProfileDto): this(
        profileDto.avatarUrl,
        profileDto.name,
        profileDto.email,
        profileDto.description,
        profileDto.specialization,
        ArrayList(profileDto.networks.map { Network(it) }),
        ArrayList(profileDto.tags.map { Tag(it, true) })
    )
}