package com.example.xhackdev.data.api

import retrofit2.http.POST
import rx.Single

interface XHackApi {

    @POST
    fun login(): Single<String>
}