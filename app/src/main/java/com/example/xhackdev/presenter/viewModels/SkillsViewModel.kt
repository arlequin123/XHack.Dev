package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.*
import com.example.xhackdev.data.api.TagsApi
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.domain.models.Tag
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class SkillsViewModel @AssistedInject constructor(
    private val tagApi: TagsApi,
    @Assisted private val selectedIds: IntArray
) : BaseViewModel() {

    private val _tagList: MutableLiveData<List<Tag>> = MutableLiveData()
    val tagList: LiveData<List<Tag>> = _tagList

    init {
        viewModelScope.launch {
            loadContent()
        }
    }


    override suspend fun loadContent() {
        try {
            _isLoading.postValue(true)
            val response = tagApi.getTags()
            _isLoading.postValue(false)

            if (response.isSuccessful) {
                response.body()?.let { it ->
                    _tagList.value = it.map { dto -> Tag(dto, selectedIds.any { id -> id == dto.id }) }
                }
            } else {
                val qwe = "oshibka"
            }
        } catch (e: Exception) {

        } finally {
            _isLoading.postValue(false)
        }
    }


    @AssistedFactory
    interface Factory {
        fun create(selectedIds: IntArray): SkillsViewModel
    }


    companion object {
        fun provideFactory(
            assitedFactory: Factory,
            selectedIds: IntArray
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assitedFactory.create(selectedIds) as T
            }
        }
    }
}