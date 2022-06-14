package com.example.xhackdev.domain.interfaces

import androidx.paging.PagingData
import com.example.xhackdev.data.models.HackDto
import kotlinx.coroutines.flow.Flow

interface HackRemoteDataSource {
    fun getHacks(): Flow<PagingData<HackDto>>
}