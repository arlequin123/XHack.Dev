package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.models.RequestDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.domain.models.RequestItem
import com.example.xhackdev.domain.models.RequestsToTeam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OutgoingRequestsViewModel @Inject constructor(): BaseViewModel() {

    private val _requests = MutableLiveData<MutableList<RequestsToTeam>>()
    val requests: LiveData<MutableList<RequestsToTeam>> = _requests

    init {
        viewModelScope.launch {
            loadContent()
        }
    }

    override suspend fun loadContent() {

    }
}