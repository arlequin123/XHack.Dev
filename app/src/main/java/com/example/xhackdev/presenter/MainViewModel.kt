package com.example.xhackdev.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.storage.AccessTokenStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

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