package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.UsersApi
import com.example.xhackdev.data.models.ProfileDto
import com.example.xhackdev.data.room.CurrentUserDao
import com.example.xhackdev.data.room.entities.toProfileDto
import com.example.xhackdev.domain.models.ProfileModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val usersApi: UsersApi,
    private val userDao: CurrentUserDao,
    private val gson: Gson
) : BaseViewModel() {

    private val _userInfo: MutableLiveData<ProfileModel> = MutableLiveData<ProfileModel>()
    val userInfo: LiveData<ProfileModel> = _userInfo

    init {
        viewModelScope.launch {
            loadContent()
        }
    }


    override suspend fun loadContent() {
        _userInfo.value = ProfileModel(userDao.getCurrentUser().toProfileDto(gson))
    }
}