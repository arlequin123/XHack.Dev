package com.example.xhackdev.domain.repository
import com.example.xhackdev.utils.Result

interface AuthRepository {

    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun registration(email: String, password: String, name: String): Result<Unit>
}