package com.example.xhackdev.domain.models

import com.example.xhackdev.data.models.RequestDto
import com.example.xhackdev.domain.interfaces.IGroup

class RequestsToTeam {

    private val _request: List<RequestDto> = emptyList()
    val size = 0


    fun get(position: Int): RequestDto? {
        if (position !in 0.._request.size-1) return null
    }
}