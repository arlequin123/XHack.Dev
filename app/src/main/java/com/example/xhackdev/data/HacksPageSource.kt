package com.example.xhackdev.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.xhackdev.data.api.HackathonApi
import com.example.xhackdev.data.models.HackDto
import com.example.xhackdev.data.models.HackathonListRequestDto
import com.example.xhackdev.data.repository.HACK_PAGE_SIZE
import kotlinx.coroutines.delay
import retrofit2.HttpException

class HacksPageSource(private val hacksApi: HackathonApi): PagingSource<Int, HackDto>() {
    override fun getRefreshKey(state: PagingState<Int, HackDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HackDto> {
        val pageNumber = params.key ?: 1

        return try {
            val response = hacksApi.getHackathonsList(HackathonListRequestDto(HACK_PAGE_SIZE, pageNumber))
            if(response.isSuccessful){
                val data = checkNotNull(response.body())
                val nextKey =
                    if (data.isEmpty()) {
                        null
                    } else {
                        pageNumber + (params.loadSize / HACK_PAGE_SIZE)
                    }
                val prevKey = if(pageNumber == 1) null else pageNumber-1
                return LoadResult.Page(data, prevKey, nextKey)
            }
            LoadResult.Error(HttpException(response))
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}