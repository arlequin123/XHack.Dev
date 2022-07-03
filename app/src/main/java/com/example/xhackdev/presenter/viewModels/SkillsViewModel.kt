package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.*
import com.example.xhackdev.data.api.TagsApi
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.domain.models.Tag
import com.example.xhackdev.domain.usecases.GetTagsUseCase
import com.example.xhackdev.utils.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class SkillsViewModel @AssistedInject constructor(
    private val getTagsUseCase: GetTagsUseCase,
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
        when (val response = getTagsUseCase.execute()) {
            is Result.Success -> {
                response.data?.let {
                    _tagList.value = it.map { dto -> Tag(dto, selectedIds.any { id -> id == dto.id }) }
                }
            }
            is Result.Error -> {

            }
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