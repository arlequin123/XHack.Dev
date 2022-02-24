package com.example.xhackdev.domain.repository

interface AuthRepository {

    fun login(email: String, password: String): String

    fun registration(email: String, password: String)
}