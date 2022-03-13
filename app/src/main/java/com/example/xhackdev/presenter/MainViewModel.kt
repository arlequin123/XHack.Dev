package com.example.xhackdev.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.XHackApi
import com.example.xhackdev.data.storage.AccessTokenStorage
import com.example.xhackdev.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import rx.schedulers.Schedulers

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val accessTokenStorage: AccessTokenStorage) : ViewModel() {

    private val _isSingIn = MutableLiveData<Boolean>()
    val isSingIn: LiveData<Boolean> = _isSingIn

    init {
        viewModelScope.launch {
            val token = accessTokenStorage.getAccessToken()
            _isSingIn.value = token.isNotEmpty()
        }
    }

}