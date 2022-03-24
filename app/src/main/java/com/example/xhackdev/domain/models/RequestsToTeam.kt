package com.example.xhackdev.domain.models

import com.example.xhackdev.data.models.RequestDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.utils.getPositionInformation

class RequestsToTeam(requests: List<RequestItem>, val requestType: RequestType) {

    private val _request: List<RequestItem> = requests
    val size = _request.size

    fun get(position: Int) = _request[position]
}