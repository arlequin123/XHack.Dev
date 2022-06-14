package com.example.xhackdev.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T: Any> Gson.fromJson(json: String): T {
    val type: Type = object : TypeToken<T>() {}.type
    return this.fromJson(json, type)
}

inline fun <reified T: Any> Gson.toJsonFromObject(obj: T): String {
    val type: Type = object : TypeToken<T>() {}.type
    return this.toJson(obj, type)
}