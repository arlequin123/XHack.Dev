package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.domain.models.RequestsToTeam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(teamsApi: TeamsApi): ViewModel() {

    val users = MutableLiveData<MutableList<RequestsToTeam>>(mutableListOf())

    init {
        for (n in 1..10){
            users.value?.add(RequestsToTeam())
        }

        viewModelScope.launch {
            //val response = teamsApi.getTeamsRequests()
        }
    }
}