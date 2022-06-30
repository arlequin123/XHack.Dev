package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.*
import com.example.xhackdev.data.api.BookmarkApi
import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.models.TeamBookmarkRequest
import com.example.xhackdev.data.models.TeamDetailsDto
import com.example.xhackdev.data.models.UserBookmarkRequest
import com.example.xhackdev.data.models.UserDetailsDto
import com.example.xhackdev.domain.models.TeamDetailsModel
import com.example.xhackdev.domain.usecases.GetTeamsDetailsRequestUseCase
import com.example.xhackdev.utils.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class TeamDetailsViewModel @AssistedInject constructor(
    private val bookmarkApi: BookmarkApi,
    private val getTeamsDetailsRequestUseCase: GetTeamsDetailsRequestUseCase,
    @Assisted private val teamId: Int
) :
    BaseViewModel() {

    private val _teamInfo = MutableLiveData<TeamDetailsModel>()
    val teamInfo: LiveData<TeamDetailsModel> = _teamInfo

    //TODO add class for _userInfo
    private val _isBookmarked = MutableLiveData<Boolean>()
    val isBookmarked: LiveData<Boolean> = _isBookmarked

    init {
        viewModelScope.launch {
            loadContent()
        }
    }


    override suspend fun loadContent() {
            _isLoading.postValue(true)
            val response = getTeamsDetailsRequestUseCase.execute(teamId)
            _isLoading.postValue(false)

        when(response){
            is Result.Success ->{
                response.data?.let {
                    _teamInfo.value = TeamDetailsModel(it)
                    _isBookmarked.value = it.isBookmarked
                }
            }
            is Result.Error ->{

            }
        }
    }


    fun addOrRemoveFavourites(){
        viewModelScope.launch {
            try {

                val response = when(_isBookmarked.value!!){
                    true -> bookmarkApi.removeTeamFromBookmark(TeamBookmarkRequest(_teamInfo.value!!.id))
                    false -> bookmarkApi.addTeamToBookmark(TeamBookmarkRequest(_teamInfo.value!!.id))
                }

                if(response.isSuccessful){
                    _isBookmarked.value = !_isBookmarked.value!!
                } else{
                    val asd = "asd"
                }
            } catch (e: Exception){
                val qwe = e.message
            } finally {

            }
        }
    }


    @AssistedFactory
    interface Factory {
        fun create(teamId: Int): TeamDetailsViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            teamId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(teamId) as T
            }
        }
    }
}