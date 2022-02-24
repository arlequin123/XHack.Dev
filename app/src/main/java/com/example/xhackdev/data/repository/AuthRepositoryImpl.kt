package com.example.xhackdev.data.repository

import com.example.xhackdev.data.api.XHackApi
import com.example.xhackdev.domain.repository.AuthRepository

class AuthRepositoryImpl(private val api: XHackApi): AuthRepository {

    override fun login(email: String, password: String): String {
        return ""
    }

    override fun registration(email: String, password: String) {

    }

}