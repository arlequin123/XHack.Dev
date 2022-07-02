package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.UsersApi
import com.example.xhackdev.data.models.ProfileDto
import com.example.xhackdev.data.models.RequestDto
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.data.room.CurrentUserDao
import com.example.xhackdev.data.room.entities.CurrentUserEntity
import com.example.xhackdev.data.storage.AccessTokenStorage
import com.example.xhackdev.domain.models.ProfileModel
import com.example.xhackdev.domain.models.RequestItem
import com.example.xhackdev.domain.models.RequestsToTeam
import com.example.xhackdev.domain.usecases.GetProfileUseCase
import com.example.xhackdev.utils.Result
import com.example.xhackdev.utils.toJsonFromObject
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDao: CurrentUserDao,
    private val storage: AccessTokenStorage,
    private val getProfileUseCase: GetProfileUseCase
) : BaseViewModel() {


    private val _userInfo: MutableLiveData<ProfileModel> = MutableLiveData<ProfileModel>()
    val userInfo: LiveData<ProfileModel> = _userInfo

    init {
        viewModelScope.launch {
            loadContent()
        }
    }


    override suspend fun loadContent() {
        when (val response = getProfileUseCase.execute()) {
            is Result.Success -> {
                response.data?.let {
                    _userInfo.value = ProfileModel(it)
                }
            }
            is Result.Error -> {

            }
        }
    }


    suspend fun logOut() {
        userDao.deleteUser(userDao.getCurrentUser())
        storage.clearAccessToken()
    }
}