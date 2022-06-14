package com.example.xhackdev.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.xhackdev.data.api.HackathonApi
import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.models.HackDto
import com.example.xhackdev.data.models.HackathonListRequestDto
import com.example.xhackdev.data.models.RequestDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.data.repository.HACK_PAGE_SIZE
import com.example.xhackdev.data.repository.HackRemoteDataSourceImpl
import com.example.xhackdev.domain.models.RequestItem
import com.example.xhackdev.domain.models.RequestsToTeam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HacksViewModel @Inject constructor(private val hacksApi: HackathonApi): BaseViewModel() {

    val hacks: Flow<PagingData<HackDto>>
    private val rep = HackRemoteDataSourceImpl(hacksApi)

    init {
        rep.getHacks().cachedIn(viewModelScope).also { hacks = it }
    }
}