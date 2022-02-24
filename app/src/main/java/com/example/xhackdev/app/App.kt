package com.example.xhackdev.app

import android.app.Application
import com.example.xhackdev.data.api.XHackApi
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class App: Application() {

    lateinit var xhackApi: XHackApi

    override fun onCreate() {
        super.onCreate()
    }
}