package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.models.RequestDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.domain.models.RequestItem
import com.example.xhackdev.domain.models.RequestsToTeam
import com.example.xhackdev.domain.usecases.GetTeamsRequestsUseCase
import com.example.xhackdev.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getTeamsRequestsUseCase: GetTeamsRequestsUseCase):
    BaseViewModel() {

    private val _requests = MutableLiveData<MutableList<RequestsToTeam>>()
    val requests: LiveData<MutableList<RequestsToTeam>> = _requests

    init {
        viewModelScope.launch {
            loadContent()
        }
    }


    override suspend fun loadContent() {

        _isLoading.value = true
        val response = getTeamsRequestsUseCase.execute()
        _isLoading.value = false

        when (response) {
            is Result.Success -> {
                response.data?.let {
                    _requests.value = it
                }
            }
            is Result.Error -> {

            }
        }

    }
}