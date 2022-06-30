package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.data.room.entities.CurrentUserEntity
import com.example.xhackdev.domain.usecases.LoginUseCase
import com.google.gson.reflect.TypeToken
import com.example.xhackdev.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val sf = MutableSharedFlow<Unit>()

    fun tryLogin(email: String, password: String) {

        viewModelScope.launch {

            _isLoading.postValue(true)
            val response = loginUseCase.execute(email, password)
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