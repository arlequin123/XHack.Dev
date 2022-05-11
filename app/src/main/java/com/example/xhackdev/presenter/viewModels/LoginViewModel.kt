package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.AuthApi
import com.example.xhackdev.data.models.LoginRequestDto
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.data.room.CurrentUserDao
import com.example.xhackdev.data.room.entities.CurrentUserEntity
import com.example.xhackdev.data.storage.AccessTokenStorage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: AuthApi,
    private val storage: AccessTokenStorage,
    private val userDao: CurrentUserDao,
    private val gson: Gson
) : BaseViewModel() {

    val sf = MutableSharedFlow<Unit>()

    fun tryLogin(email: String, password: String) {

        viewModelScope.launch {
            try {
                _isLoading.postValue(true)
                val response = api.login(LoginRequestDto(email, password))
                _isLoading.postValue(false)

                //todo refactor shi
                if (response.isSuccessful) {
                    response.body()?.let {
                        storage.saveAccessToken(it.token)
                        val tagType: Type = object : TypeToken<List<TagDto>>() {}.type
                        val userEntity = CurrentUserEntity(it.user.id, if(it.user.avatarUrl.isNullOrEmpty()) "" else it.user.avatarUrl, it.user.name, it.user.email, it.user.description, it.user.specialization, gson.toJson(it.user.networks), gson.toJson(
                            emptyList<TagDto>(), tagType))
                        userDao.addUser(userEntity)

                        sf.emit(Unit)
                    }
                } else {
                    val qwe = "net polzovatelya"
                }
            } catch (e: Exception) {

            } finally {
                _isLoading.postValue(false)
            }
        }

    }
}