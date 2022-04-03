package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.xhackdev.data.models.RequestDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.domain.models.RequestItem
import com.example.xhackdev.domain.models.RequestsToTeam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel() {

    val items = MutableLiveData<List<Any>>()

    init {
        viewModelScope.launch {
            loadContent()
            items.value = List(10){1}
        }
    }


//    override suspend fun loadContent() {
//        try {
//            _isLoading.postValue(true)
//            val response =
//            _isLoading.postValue(false)
//
//            if (response.isSuccessful) {
//                response.body()?.let { it ->
//
//
//                }
//            } else {
//                val qwe = "oshibka"
//            }
//        } catch (e: Exception) {
//
//        } finally {
//            _isLoading.postValue(false)
//        }
//    }
}