package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.*
import com.example.xhackdev.data.api.BookmarkApi
import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.api.UsersApi
import com.example.xhackdev.data.models.UserBookmarkRequest
import com.example.xhackdev.data.models.UserDetailsDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch


class UserDetailsViewModel @AssistedInject constructor(
    private val bookmarkApi: BookmarkApi,
    private val teamsApi: TeamsApi,
    private val usersApi: UsersApi,
    @Assisted private val userId: Int
    ): BaseViewModel() {

    private val _userInfo = MutableLiveData<UserDetailsDto>()
    val userInfo: LiveData<UserDetailsDto> = _userInfo

    //TODO add class for _userInfo
    private val _isBookmarked = MutableLiveData<Boolean>()
    val isBookmarked: LiveData<Boolean> = _isBookmarked

    init {
        viewModelScope.launch {
            loadContent()
        }
    }


    override suspend fun loadContent() {
        try {
            _isLoading.postValue(true)
            val response = usersApi.getUser(userId)
            _isLoading.postValue(false)

            if(response.isSuccessful){
                response.body()?.let {
                    _userInfo.value = it
                    _isBookmarked.value = it.isBookmarked
                }
            }else{
                val qwe = "govno"
            }
        } catch (e:Exception){
            val qwe = e.message
        } finally {

        }
    }


    fun addOrRemoveFavourites(){
        viewModelScope.launch {
            try {

                val response = when(_isBookmarked.value!!){
                    true -> bookmarkApi.removeUserFromBookmark(UserBookmarkRequest(_userInfo.value!!.id))
                    false -> bookmarkApi.addUserToBookmark(UserBookmarkRequest(_userInfo.value!!.id))
                }

                if(response.isSuccessful){
                    _isBookmarked.value = !_isBookmarked.value!!
                } else{
                    val asd = "asd"
                }
            } catch (e: Exception){
                val qwe = e.message
            } finally {

            }
        }
    }

    fun acceptRequestFromUser(requestId: Int){
        viewModelScope.launch {
            try {
                val response = teamsApi.acceptRequestUserToTeam(requestId)

                if (response.isSuccessful) {

                } else {
                    val qwe = "govno"
                }
            } catch (e: Exception) {
                val qwe = e.message
            } finally {

            }
        }
    }

    fun declineRequestFromUser(requestId: Int){
        viewModelScope.launch {
            try {
                _isLoading.postValue(true)
                val response = teamsApi.declineRequestUserToTeam(requestId)
                _isLoading.postValue(false)

                if (response.isSuccessful) {

                } else {
                    val qwe = "govno"
                }
            } catch (e: Exception) {
                val qwe = e.message
            } finally {

            }
        }
    }

    fun withdrowRequestToUser(requestId: Int){
        viewModelScope.launch {
            try {
                val response = teamsApi.withdrawRequest(requestId)

                if (response.isSuccessful) {

                } else {
                    val qwe = "govno"
                }
            } catch (e: Exception) {
                val qwe = e.message
            } finally {

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