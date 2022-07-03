package com.example.xhackdev.data.repository

import com.example.xhackdev.data.api.BookmarkApi
import com.example.xhackdev.data.models.TeamBookmarkRequest
import com.example.xhackdev.data.models.TeamsRequestResponseDto
import com.example.xhackdev.data.models.UserBookmarkRequest
import com.example.xhackdev.domain.repository.BookmarkRepository
import com.example.xhackdev.utils.Result

class BookmarkRepositoryImpl(private val bookMarkApi: BookmarkApi): BookmarkRepository {

    override suspend fun addUserToBookmark(userId: UserBookmarkRequest): Result<Unit> {
        val result: Result<Unit> = try {
            val response = bookMarkApi.addUserToBookmark(userId)
            if(response.isSuccessful){
                Result.Success(Unit)
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

    override suspend fun removeUserFromBookmark(userId: UserBookmarkRequest): Result<Unit> {
        val result: Result<Unit> = try {
            val response = bookMarkApi.removeUserFromBookmark(userId)
            if(response.isSuccessful){
                Result.Success(Unit)
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

    override suspend fun addTeamToBookmark(teamId: TeamBookmarkRequest): Result<Unit> {
        val result: Result<Unit> = try {
            val response = bookMarkApi.addTeamToBookmark(teamId)
            if(response.isSuccessful){
                Result.Success(Unit)
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

    override suspend fun removeTeamFromBookmark(teamId: TeamBookmarkRequest): Result<Unit> {
        val result: Result<Unit> = try {
            val response = bookMarkApi.removeTeamFromBookmark(teamId)
            if(response.isSuccessful){
                Result.Success(Unit)
            } else{
                Result.Error("Упс, произошла непредвиденная ошибка")
            }
        } catch (exception: Exception){
            Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }
}