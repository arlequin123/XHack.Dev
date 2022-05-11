package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.AuthApi
import com.example.xhackdev.data.models.RegisterRequestDto
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
import kotlin.Exception

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val api: AuthApi,
    private val storage: AccessTokenStorage,
    private val currentUserDao: CurrentUserDao,
    private val gson: Gson
) : BaseViewModel() {

    val sf = MutableSharedFlow<Unit>()

    fun tryRegister(email: String, password: String, name: String) {
        viewModelScope.launch {
            try {
                _isLoading.postValue(true)
                val response = api.register(RegisterRequestDto(email, password, name))
                _isLoading.postValue(false)

                if (response.isSuccessful) {
                    response.body()?.let {
                        storage.saveAccessToken(it.token)
                        val type: Type = object : TypeToken<List<TagDto>>() {}.type
                        currentUserDao.addUser(CurrentUserEntity(it.id, if(it.avatarUrl.isNullOrEmpty()) "" else it.avatarUrl, it.name, it.email, it.description, it.specialization, gson.toJson(it.networks), gson.toJson(null, type)))
                        sf.emit(Unit)
                    }
                } else {
                    val qwe = "takoi uzhe est"
                }
            } catch (e: Exception) {

            }
            finally {
                _isLoading.postValue(false)
            }
        }
    }
}