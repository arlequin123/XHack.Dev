package com.example.xhackdev.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.xhackdev.data.HacksPageSource
import com.example.xhackdev.data.api.HackathonApi
import com.example.xhackdev.data.models.HackDto
import com.example.xhackdev.domain.interfaces.HackRemoteDataSource
import kotlinx.coroutines.flow.Flow

const val HACK_PAGE_SIZE = 10

class HackRemoteDataSourceImpl(private val hacksApi: HackathonApi): HackRemoteDataSource {
    override fun getHacks(): Flow<PagingData<HackDto>> {
        return  Pager(
            config = PagingConfig(HACK_PAGE_SIZE, enablePlaceholders = false, initialLoadSize = HACK_PAGE_SIZE, prefetchDistance = 5),
            pagingSourceFactory = {
                HacksPageSource(hacksApi)
            }
        ).flow
    }
}