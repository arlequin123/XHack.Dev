package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.UserBookmarkRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BookmarkApi {

    @POST("/api/bookmarks/bookmark-user")
    suspend fun addUserToBookmark(@Body userId: UserBookmarkRequest): Response<Unit>

    @POST("/api/bookmarks/remove-user-bookmark")
    suspend fun removeUserFromBookmark(@Body userId: UserBookmarkRequest): Response<Unit>
}