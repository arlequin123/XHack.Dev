package com.example.xhackdev.presenter.viewModels

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(): BaseViewModel() {

    private var userId = 0

    constructor(userId: Int) : this(){
        this.userId = userId
    }
}