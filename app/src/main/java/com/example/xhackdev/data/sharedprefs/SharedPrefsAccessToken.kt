package com.example.xhackdev.data.sharedprefs

import android.content.Context
import com.example.xhackdev.data.storage.AccessTokenStorage

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_ACCESS_TOKEN = "access_token"

class SharedPrefsAccessToken(context: Context) : AccessTokenStorage {

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveAccessToken() {

    }

    override fun getAccessToken() {

    }
}