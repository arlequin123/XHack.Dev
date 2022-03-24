package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.AuthApi
import com.example.xhackdev.data.models.RegisterRequestDto
import com.example.xhackdev.data.storage.AccessTokenStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val api: AuthApi,
    private val storage: AccessTokenStorage
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