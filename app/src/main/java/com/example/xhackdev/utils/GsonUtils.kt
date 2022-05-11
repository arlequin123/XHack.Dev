package com.example.xhackdev.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun <T> Gson.fromJson(json: String): T {
    val type: Type = object : TypeToken<T>() {}.type
    return this.fromJson(json, type)
}