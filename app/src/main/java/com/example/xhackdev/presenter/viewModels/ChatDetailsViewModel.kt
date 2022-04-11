package com.example.xhackdev.presenter.viewModels

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatDetailsViewModel @Inject constructor(): BaseViewModel() {

    private var chatId = 0

    constructor(chatId: Int) : this(){
     this.chatId = chatId
    }
}