package com.example.xhackdev.data.sharedprefs

import android.content.Context
import com.example.xhackdev.data.storage.AccessTokenStorage

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_ACCESS_TOKEN = "access_token"

class SharedPrefsAccessToken(context: Context) : AccessTokenStorage {

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveAccessToken(token: String) {
        sharedPrefs.edit().putString(KEY_ACCESS_TOKEN, token).apply()
    }

    override fun getAccessToken(): String {
        return sharedPrefs.getString(KEY_ACCESS_TOKEN, "").orEmpty()
    }

    override fun clearAccessToken() {
        sharedPrefs.edit().putString(KEY_ACCESS_TOKEN, "").apply()
    }
}