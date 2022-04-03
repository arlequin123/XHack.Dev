package com.example.xhackdev.presenter.viewModels

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamDetailsViewModel @Inject constructor() :
    BaseViewModel() {

    private var teamId = 0

    constructor(teamId: Int) : this() {
        this.teamId = teamId
    }

}