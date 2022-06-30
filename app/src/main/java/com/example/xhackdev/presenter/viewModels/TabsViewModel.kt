package com.example.xhackdev.presenter.viewModels

import com.example.xhackdev.data.storage.AccessTokenStorage
import com.example.xhackdev.utils.SocketHandler
import javax.inject.Inject

class TabsViewModel @Inject constructor(accessTokenStorage: AccessTokenStorage): BaseViewModel() {

    init {
        val token = accessTokenStorage.getAccessToken()
        if(SocketHandler.setSocket(token)){
            SocketHandler.connect()
        }
    }

    override fun onCleared() {
        super.onCleared()
        SocketHandler.disconnect()
    }
}