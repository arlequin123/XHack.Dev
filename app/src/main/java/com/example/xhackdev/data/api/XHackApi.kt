package com.example.xhackdev.data.api

import com.example.xhackdev.data.models.LoginDto
import retrofit2.Response
import retrofit2.http.POST
import rx.Single

interface XHackApi {

    @POST
    suspend fun login(): Response<LoginDto>
}