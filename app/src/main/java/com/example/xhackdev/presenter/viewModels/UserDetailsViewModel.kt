package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.*
import com.example.xhackdev.data.api.BookmarkApi
import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.api.UsersApi
import com.example.xhackdev.data.models.InviteUserDto
import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.data.models.UserBookmarkRequest
import com.example.xhackdev.data.models.UserDetailsDto
import com.example.xhackdev.domain.usecases.*
import com.example.xhackdev.utils.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserDetailsViewModel @AssistedInject constructor(
    private val bookmarkApi: BookmarkApi,
    private val acceptRequestUserToTeamUseCase: AcceptRequestUserToTeamUseCase,
    private val declineRequestUserToTeamUseCase: DeclineRequestUserToTeamUseCase,
    private val withdrawRequestUseCase: WithdrawRequestUseCase,
    private val sendRequestToUserUseCase: SendRequestToUserUseCase,
    private val getMyTeamsRequestUseCase: GetMyTeamsRequestUseCase,
    private val getUserUseCase: GetUserUseCase,
    @Assisted private val userId: Int
) : BaseViewModel() {

    private val _myTeams = MutableLiveData<List<ShortTeamDetailsDto>>()
    val myteams: LiveData<List<ShortTeamDetailsDto>> = _myTeams


    private val _userInfo = MutableLiveData<UserDetailsDto>()
    val userInfo: LiveData<UserDetailsDto> = _userInfo


    private val _isBookmarked = MutableLiveData<Boolean>()
    val isBookmarked: LiveData<Boolean> = _isBookmarked

    init {
        viewModelScope.launch {
            loadContent()
        }
    }


    override suspend fun loadContent() {
        when (val response = getUserUseCase.execute(userId)) {
            is Result.Success -> {
                response.data?.let {
                    _userInfo.value = it
                    _isBookmarked.value = it.isBookmarked
                }
            }
            is Result.Error -> {

            }
        }
    }


    fun getMyteams() {
        viewModelScope.launch {
            when (val response = getMyTeamsRequestUseCase.execute()) {
                is Result.Success -> {
                    response.data?.let {
                        _myTeams.value = it
                    }
                }
                is Result.Error -> {

                }
            }
        }
    }


    fun sendRequestToUser(teamId: Int) {
        viewModelScope.launch {
            when (sendRequestToUserUseCase.execute(InviteUserDto(userId, teamId))) {
                is Result.Success -> {
                    loadContent()
                }
                is Result.Error -> {

                }
            }
        }
    }


    fun addOrRemoveFavourites() {
        viewModelScope.launch {
            try {

                val response = when (_isBookmarked.value!!) {
                    true -> bookmarkApi.removeUserFromBookmark(UserBookmarkRequest(_userInfo.value!!.id))//todo fix !!.id
                    false -> bookmarkApi.addUserToBookmark(UserBookmarkRequest(_userInfo.value!!.id))
                }

                if (response.isSuccessful) {
                    _isBookmarked.value = !_isBookmarked.value!!
                } else {
                    val asd = "asd"
                }
            } catch (e: Exception) {
                val qwe = e.message
            } finally {

            }
        }
    }

    fun acceptRequestFromUser(requestId: Int) {
        viewModelScope.launch {
            when (acceptRequestUserToTeamUseCase.execute(requestId)) {
                is Result.Success -> {

                }
                is Result.Error -> {

                }
            }
        }
    }

    fun declineRequestFromUser(requestId: Int) {
        viewModelScope.launch {
            when (declineRequestUserToTeamUseCase.execute(requestId)) {
                is Result.Success -> {

                }
                is Result.Error -> {

                }
            }
        }
    }

    fun withdrowRequestToUser(requestId: Int) {
        viewModelScope.launch {
            withdrawRequestUseCase
            when (withdrawRequestUseCase.execute(requestId)) {
                is Result.Success -> {

                }
                is Result.Error -> {

                }
            }
        }
    }


    @AssistedFactory
    interface Factory {
        fun create(userId: Int): UserDetailsViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            userId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(userId) as T
            }
        }
    }
}