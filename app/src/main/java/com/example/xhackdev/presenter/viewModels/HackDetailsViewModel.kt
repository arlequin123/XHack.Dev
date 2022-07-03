package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.*
import com.example.xhackdev.data.api.BookmarkApi
import com.example.xhackdev.data.api.HackathonApi
import com.example.xhackdev.data.models.HackDetailDto
import com.example.xhackdev.domain.models.HackDetail
import com.example.xhackdev.domain.models.Tag
import com.example.xhackdev.domain.models.TeamDetailsModel
import com.example.xhackdev.domain.usecasesGetHackDetailUseCase.GetHackDetailUseCase
import com.example.xhackdev.utils.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class HackDetailsViewModel @AssistedInject constructor(
    private val getHackDetailUseCase: GetHackDetailUseCase,
    @Assisted private val hackId: Int
) : BaseViewModel() {


    private val _isBookmarked = MutableLiveData<Boolean>()
    val isBookmarked: LiveData<Boolean> = _isBookmarked

    private val _hack = MutableLiveData<HackDetail>()
    val hack: LiveData<HackDetail> = _hack


    init {
        viewModelScope.launch {
            loadContent()
        }
    }


    override suspend fun loadContent() {
        when (val response = getHackDetailUseCase.execute(hackId)) {
            is Result.Success -> {
                response.data?.let {
                    _hack.value = HackDetail(it)
                    _isBookmarked.value = it.isBookmarked
                }
            }
            is Result.Error -> {

            }
        }
    }

    fun addOrRemoveFavourites(){

    }


    @AssistedFactory
    interface Factory{
        fun create(hackId: Int): HackDetailsViewModel
    }

    companion object{
        fun provideFactory(
            assistedFactory: Factory,
            hackId: Int
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(hackId) as T
            }
        }
    }
}