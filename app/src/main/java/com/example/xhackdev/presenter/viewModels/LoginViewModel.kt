package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.AuthApi
import com.example.xhackdev.data.models.LoginRequestDto
import com.example.xhackdev.data.storage.AccessTokenStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: AuthApi,
    private val storage: AccessTokenStorage
) : BaseViewModel() {


    val sf = MutableSharedFlow<Unit>()

    fun tryLogin(email: String, password: String) {

        viewModelScope.launch {
            try {
                _isLoading.postValue(true)

                val response = api.login(LoginRequestDto(email, password))
                if (response.isSuccessful) {
                    val dto = response.body()
                    if (dto != null) {
                        storage.saveAccessToken(dto.token)
                        sf.emit(Unit)
                    }
                } else {
                    val qwe = "net polzovatelya"
                }
            } catch (e: Exception) {
                val asd = "govno server"
            }
            finally {
                _isLoading.postValue(false)
            }

        }

    }
}