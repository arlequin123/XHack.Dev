package com.example.xhackdev.data.repository

import com.example.xhackdev.data.api.BookmarkApi
import com.example.xhackdev.data.models.TeamBookmarkRequest
import com.example.xhackdev.data.models.UserBookmarkRequest
import com.example.xhackdev.domain.repository.BookmarkRepository
import com.example.xhackdev.utils.Result

class BookmarkRepositoryImpl(private val bookMarkApi: BookmarkApi): BookmarkRepository {

    override suspend fun addUserToBookmark(userId: UserBookmarkRequest): Result<Unit> {

    }

    override suspend fun removeUserFromBookmark(userId: UserBookmarkRequest): Result<Unit> {

    }

    override suspend fun addTeamToBookmark(teamId: TeamBookmarkRequest): Result<Unit> {

    }

    override suspend fun removeTeamFromBookmark(teamId: TeamBookmarkRequest): Result<Unit> {
        
    }
}