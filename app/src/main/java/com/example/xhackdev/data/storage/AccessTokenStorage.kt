package com.example.xhackdev.data.storage

interface AccessTokenStorage {

    fun saveAccessToken()

    fun getAccessToken()
}