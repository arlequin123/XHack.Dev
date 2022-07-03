package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.models.UserBookmarkRequest
import com.example.xhackdev.domain.repository.BookmarkRepository
import com.example.xhackdev.utils.Result

class RemoveUserFromBookmarkUseCase(private val bookmarkRepository: BookmarkRepository) {
    suspend fun execute(userId: UserBookmarkRequest): Result<Unit> {
        return bookmarkRepository.removeUserFromBookmark(userId)
    }
}