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
import com.example.xhackdev.utils.toJsonFromObject
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val usersApi: UsersApi,
    private val userDao: CurrentUserDao,
    private val storage: AccessTokenStorage,
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
        try {
            _isLoading.postValue(true)
            val response = usersApi.getProfile()
            _isLoading.postValue(false)

            if (response.isSuccessful) {
                response.body()?.let { it ->
                    _userInfo.value = ProfileModel(it)
                    val userEntity = CurrentUserEntity(it.id, if(it.avatarUrl.isNullOrEmpty()) "" else it.avatarUrl, it.name, it.email, it.description, it.specialization, gson.toJson(it.networks), gson.toJsonFromObject(
                        it.tags))
                    userDao.updateUser(userEntity)
                }
            } else {
                val qwe = "oshibka"
            }
        } catch (e: Exception) {

        } finally {
            _isLoading.postValue(false)
        }
    }


    suspend fun logOut(){
        userDao.deleteUser(userDao.getCurrentUser())
        storage.clearAccessToken()
    }
}