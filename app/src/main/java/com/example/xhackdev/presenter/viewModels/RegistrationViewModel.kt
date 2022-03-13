package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.XHackApi
import com.example.xhackdev.data.models.RegisterRequestDto
import com.example.xhackdev.data.storage.AccessTokenStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val api: XHackApi,
    private val storage: AccessTokenStorage
) : ViewModel() {

    val pb = PublishSubject.create<Unit>()

    fun tryRegister(email: String, password: String, name: String) {
        viewModelScope.launch {
            try {
                val response = api.register(RegisterRequestDto(email, password, name))
                if (response.isSuccessful) {
                    val dto = response.body()
                    if (dto != null) {
                        storage.saveAccessToken(dto.token)
                        pb.onNext(Unit)
                    }
                } else {
                    val qwe = "takoi uzhe est"
                }
            } catch (e: Exception) {
                val asd = "govno server"

                //test
                pb.onNext(Unit)
            }
        }
    }
}