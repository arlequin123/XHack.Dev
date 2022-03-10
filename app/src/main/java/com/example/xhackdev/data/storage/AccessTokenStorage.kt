package com.example.xhackdev.data.storage

interface AccessTokenStorage {

    fun saveAccessToken(token: String)

    fun getAccessToken(): String
}