package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.AuthApi
import com.example.xhackdev.data.models.RegisterRequestDto
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.data.room.CurrentUserDao
import com.example.xhackdev.data.room.entities.CurrentUserEntity
import com.example.xhackdev.data.storage.AccessTokenStorage
import com.example.xhackdev.domain.usecases.RegistrationUseCase
import com.example.xhackdev.utils.Result
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
    private val registrationUseCase: RegistrationUseCase
) : BaseViewModel() {

    val sf = MutableSharedFlow<Unit>()

    fun tryRegister(email: String, password: String, name: String) {
        viewModelScope.launch {

            _isLoading.postValue(true)
            val response = registrationUseCase.execute(email, password, name)
            _isLoading.postValue(false)

            when (response) {
                is Result.Success -> {
                    sf.emit(Unit)
                }
                is Result.Error -> {

                }
            }
        }
    }
}