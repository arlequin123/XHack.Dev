package com.example.xhackdev.presenter.viewModels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.api.FileApi
import com.example.xhackdev.data.api.UsersApi
import com.example.xhackdev.data.models.ProfileDto
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.data.room.CurrentUserDao
import com.example.xhackdev.data.room.entities.CurrentUserEntity
import com.example.xhackdev.data.room.entities.toProfileDto
import com.example.xhackdev.domain.models.ProfileModel
import com.example.xhackdev.domain.models.Tag
import com.example.xhackdev.domain.usecases.UpdateProfileUseCase
import com.example.xhackdev.utils.Result
import com.example.xhackdev.utils.toJsonFromObject
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val usersApi: UsersApi,
    private val fileApi: FileApi,
    private val userDao: CurrentUserDao,
    private val gson: Gson,
    private val updateProfileUseCase: UpdateProfileUseCase
) : BaseViewModel() {

    private var _userId: Int = 0
    private var _email: String = ""

    private val _userInfo: MutableLiveData<ProfileModel> = MutableLiveData<ProfileModel>()
    val userInfo: LiveData<ProfileModel> = _userInfo

    private val _avatarUrl: MutableLiveData<String?> = MutableLiveData<String?>()
    val avatarUrl: LiveData<String?> = _avatarUrl

    val sf = MutableSharedFlow<Unit>()

    init {
        viewModelScope.launch {
            loadContent()
        }
    }

    override suspend fun loadContent() {
        val user = userDao.getCurrentUser()
        _userInfo.value = ProfileModel(user.toProfileDto(gson))
        _avatarUrl.value = _userInfo.value!!.avatarUrl
        _userId = user.id
        _email = user.email
    }

    fun setTagList(tagList: List<Tag>){
        _userInfo.value?.tags = ArrayList(tagList)
    }


    fun saveContent(userName: String, specialization: String, networks: List<String>, tags: List<TagDto>){
        viewModelScope.launch {
            when (updateProfileUseCase.execute(ProfileDto(_userId, _avatarUrl.value, userName, _email, "", specialization, networks, tags))) {
                is Result.Success -> {
                    sf.emit(Unit)
                }
                is Result.Error -> {

                }
            }
        }
    }


    fun uploadImage(body: MultipartBody.Part){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = fileApi.uploadFile(body)
                _isLoading.value = false

                if (response.isSuccessful) {
                    response.body()?.let { it ->
                        _avatarUrl.value = it.imageUrl
                    }
                } else {
                    val qwe = "oshibka"
                }
            }catch (e: Exception) {
                val qwe = e.message
            }finally {

            }
        }
    }
}