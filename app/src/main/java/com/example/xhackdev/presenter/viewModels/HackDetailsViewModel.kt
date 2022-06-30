package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.*
import com.example.xhackdev.data.api.BookmarkApi
import com.example.xhackdev.data.api.HackathonApi
import com.example.xhackdev.data.models.HackDetailDto
import com.example.xhackdev.domain.models.TeamDetailsModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class HackDetailsViewModel @AssistedInject constructor(
    private val hackApi: HackathonApi,
    private val bookMarkApi: BookmarkApi,
    @Assisted private val hackId: Int
) : BaseViewModel() {


    private val _isBookmarked = MutableLiveData<Boolean>()
    val isBookmarked: LiveData<Boolean> = _isBookmarked

    private val _hack = MutableLiveData<HackDetailDto>()
    val hack: LiveData<HackDetailDto> = _hack


    init {
        viewModelScope.launch {
            loadContent()
        }
    }


    override suspend fun loadContent() {
        try {
            _isLoading.postValue(true)
            val response = hackApi.getHackDetail(hackId)
            _isLoading.postValue(false)

            if(response.isSuccessful){
                response.body()?.let {
                    _hack.value = it
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