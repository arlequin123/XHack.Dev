package com.example.xhackdev.domain.repository

import com.example.xhackdev.data.models.TeamBookmarkRequest
import com.example.xhackdev.data.models.UserBookmarkRequest
import com.example.xhackdev.utils.Result


interface BookmarkRepository {
    suspend fun addUserToBookmark(userId: UserBookmarkRequest): Result<Unit>

    suspend fun removeUserFromBookmark(userId: UserBookmarkRequest): Result<Unit>

    suspend fun addTeamToBookmark(teamId: TeamBookmarkRequest): Result<Unit>

    suspend fun removeTeamFromBookmark(teamId: TeamBookmarkRequest): Result<Unit>
}