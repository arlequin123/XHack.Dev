package com.example.xhackdev.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xhackdev.data.api.XHackApi
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import rx.schedulers.Schedulers

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _isReady = MutableLiveData(false)
    val isReady: LiveData<Boolean> = _isReady

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun tryLogin(){

    }
}