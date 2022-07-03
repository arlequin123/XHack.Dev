package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.models.TeamBookmarkRequest
import com.example.xhackdev.domain.repository.BookmarkRepository
import com.example.xhackdev.utils.Result

class AddTeamToBookmarkUseCase(private val bookmarkRepository: BookmarkRepository) {
    suspend fun execute(teamId: TeamBookmarkRequest): Result<Unit> {
        return bookmarkRepository.addTeamToBookmark(teamId)
    }
}