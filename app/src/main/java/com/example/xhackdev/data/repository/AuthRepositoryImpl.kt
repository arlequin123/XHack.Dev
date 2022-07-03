package com.example.xhackdev.data.repository

import com.example.xhackdev.data.api.AuthApi
import com.example.xhackdev.data.models.LoginRequestDto
import com.example.xhackdev.data.models.LoginResponseDto
import com.example.xhackdev.data.models.RegisterRequestDto
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.data.room.CurrentUserDao
import com.example.xhackdev.data.room.entities.CurrentUserEntity
import com.example.xhackdev.data.storage.AccessTokenStorage
import com.example.xhackdev.domain.repository.AuthRepository
import com.example.xhackdev.utils.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import java.lang.reflect.Type

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val storage: AccessTokenStorage,
    private val userDao: CurrentUserDao,
    private val gson: Gson
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        lateinit var result: Result<Unit>
        try {
            val response = api.login(LoginRequestDto(email, password))
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if(loginResponse != null)
                {
                    storage.saveAccessToken(loginResponse.token)
                    val tagType: Type = object : TypeToken<List<TagDto>>() {}.type
                    val userEntity = CurrentUserEntity(
                        loginResponse.user.id,
                        if (loginResponse.user.avatarUrl.isNullOrEmpty()) "" else loginResponse.user.avatarUrl,
                        loginResponse.user.name,
                        loginResponse.user.email,
                        loginResponse.user.description,
                        loginResponse.user.specialization,
                        gson.toJson(loginResponse.user.networks),
                        gson.toJson(
                            emptyList<TagDto>(), tagType
                        )
                    )
                    userDao.addUser(userEntity)
                    result = Result.Success(Unit)
                }else {
                    result = Result.Error("Произошла ошибка")
                }
            } else {
                result = Result.Error("Нет такого пользователся, проверьте логин или пароль.")
            }
        } catch (e: Exception) {
            result = Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

    override suspend fun registration(email: String, password: String, name: String): Result<Unit> {
        lateinit var result: Result<Unit>
        try {
            val response = api.register(RegisterRequestDto(email, password, name ))
            if (response.isSuccessful) {
                response.body()?.let {
                    storage.saveAccessToken(it.token)
                    val type: Type = object : TypeToken<List<TagDto>>() {}.type
                    userDao.addUser(CurrentUserEntity(it.id, if(it.avatarUrl.isNullOrEmpty()) "" else it.avatarUrl, it.name, it.email, it.description, it.specialization, gson.toJson(it.networks), gson.toJson(null, type)))
                    result = Result.Success(Unit)
                }
            } else {
                result = Result.Error("Такой пользователь уже есть")
            }
        } catch (e: Exception) {
            result = Result.Error("Ошибка сервера, попробуйте позже")
        }
        return result
    }

}