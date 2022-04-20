package com.example.xhackdev.domain.models

import com.example.xhackdev.data.models.TeamDetailsRequestDto

class TeamDetailsRequestModel(teamDetailsRequestDto: TeamDetailsRequestDto) {
    var id = teamDetailsRequestDto.id
    var userId = teamDetailsRequestDto.id
    var teamId = teamDetailsRequestDto.teamId
    var type = teamDetailsRequestDto.type
}