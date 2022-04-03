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
class HacksViewModel @Inject constructor(private val teamsApi: TeamsApi): BaseViewModel() {

    private val _requests = MutableLiveData<MutableList<RequestsToTeam>>()
    val requests: LiveData<MutableList<RequestsToTeam>> = _requests

    init {
        viewModelScope.launch {
            loadContent()
        }
    }


    override suspend fun loadContent() {
        try {
            _isLoading.postValue(true)
            val response = teamsApi.getTeamsRequests()
            _isLoading.postValue(false)

            if (response.isSuccessful) {
                response.body()?.let { it ->

                    val createRequestItem = fun(request: RequestDto): RequestItem = RequestItem(
                        request.id,
                        request.user,
                        request.team,
                        request.type,
                        request.isCanceled
                    )

                    val requestsFromTeams = it.fromTeams.map(createRequestItem)
                    val requestsFromUsers = it.fromUsers.map(createRequestItem)

                    _requests.value = mutableListOf(
                        RequestsToTeam(requestsFromTeams, RequestType.TeamToUser),
                        RequestsToTeam(requestsFromUsers, RequestType.UserToTeam)
                    )
                }
            } else {
                val qwe = "oshibka"
            }
        } catch (e: Exception) {

        } finally {
            _isLoading.postValue(false)
        }
    }
}