package com.example.xhackdev.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.XHackApi
import com.example.xhackdev.data.models.LoginRequestDto
import com.example.xhackdev.data.storage.AccessTokenStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: XHackApi,
    private val storage: AccessTokenStorage
) : ViewModel() {

    val pb = PublishSubject.create<Unit>()

    fun tryLogin(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = api.login(LoginRequestDto(email, password))
                if (response.isSuccessful) {
                    val dto = response.body()
                    if (dto != null) {
                        storage.saveAccessToken(dto.token)
                        pb.onNext(Unit)
                    }
                } else {
                    val qwe = "net polzovatelya"
                }
            } catch (e: Exception) {
                val asd = "govno server"
            }

        }

    }
}