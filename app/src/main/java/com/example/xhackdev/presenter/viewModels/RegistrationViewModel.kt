package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.AuthApi
import com.example.xhackdev.data.models.RegisterRequestDto
import com.example.xhackdev.data.storage.AccessTokenStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val api: AuthApi,
    private val storage: AccessTokenStorage
) : ViewModel() {

    fun tryRegister(email: String, password: String, name: String) {
        viewModelScope.launch {
            try {
                val response = api.register(RegisterRequestDto(email, password, name))
                if (response.isSuccessful) {
                    val dto = response.body()
                    if (dto != null) {
                        storage.saveAccessToken(dto.token)
                    }
                } else {
                    val qwe = "takoi uzhe est"
                }
            } catch (e: Exception) {
                val asd = "govno server"
            }
        }
    }
}