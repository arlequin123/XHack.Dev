package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.*
import com.example.xhackdev.data.api.UsersApi
import com.example.xhackdev.data.models.UserDetailsDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserDetailsViewModel @AssistedInject constructor(private val usersApi: UsersApi, @Assisted private val userId: Int): BaseViewModel() {

    private val _userInfo = MutableLiveData<UserDetailsDto>()
    val userInfo: LiveData<UserDetailsDto> = _userInfo

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
                }
            }else{
                val qwe = "govno"
            }
        } catch (e:Exception){
            val qwe = e.message
        } finally {

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