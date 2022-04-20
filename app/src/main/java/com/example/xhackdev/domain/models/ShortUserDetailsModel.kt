package com.example.xhackdev.domain.models

import com.example.xhackdev.data.models.ShortUserDetailsDto

class ShortUserDetailsModel(shortUserDetailsDto: ShortUserDetailsDto) {
    var id: Int = shortUserDetailsDto.id
    var name: String = shortUserDetailsDto.name
    var avatarUrl: String? = shortUserDetailsDto.avatarUrl
    var networks: ArrayList<String> = ArrayList(shortUserDetailsDto.networks)
    var specialization: String = shortUserDetailsDto.specialization
    var email: String = shortUserDetailsDto.email
    var description: String = shortUserDetailsDto.description
    var isCaptain = false

    constructor(shortUserDetailsDto: ShortUserDetailsDto, isCaptain: Boolean): this(shortUserDetailsDto){
        this.isCaptain = isCaptain
    }
}