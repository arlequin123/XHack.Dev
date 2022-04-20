package com.example.xhackdev.domain.models

import com.example.xhackdev.data.models.TeamDetailsDto
import com.example.xhackdev.data.primitives.TeamParticipantType

class TeamDetailsModel(teamDetailDto: TeamDetailsDto) {
    var id: Int = teamDetailDto.id
    var name: String = teamDetailDto.name
    var description: String = teamDetailDto.description
    var maxCountUser: Int = teamDetailDto.maxCountUser
    var avatarUrl: String? = teamDetailDto.avatarUrl
    var captain: ShortUserDetailsModel = ShortUserDetailsModel(teamDetailDto.captain, true)
    var chat: Int? = teamDetailDto.chat
    var isInActiveSearch: Boolean = teamDetailDto.isInActiveSearch
    var members: ArrayList<ShortUserDetailsModel> = ArrayList(teamDetailDto.members.map { ShortUserDetailsModel(it, it.id == captain.id)})
    var requests: ArrayList<TeamDetailsRequestModel> = ArrayList(teamDetailDto.requests.map { TeamDetailsRequestModel(it) })
    var isBookmarked: Boolean = teamDetailDto.isBookmarked
    var participantType: TeamParticipantType = teamDetailDto.participantType
}