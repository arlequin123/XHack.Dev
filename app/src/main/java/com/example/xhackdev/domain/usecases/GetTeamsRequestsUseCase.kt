package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.api.TeamsApi
import com.example.xhackdev.data.models.RequestDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.domain.models.RequestItem
import com.example.xhackdev.domain.models.RequestsToTeam
import com.example.xhackdev.domain.repository.TeamsRepository
import com.example.xhackdev.utils.Result

class GetTeamsRequestsUseCase(private val teamsRepository: TeamsRepository) {

    suspend fun execute(): Result<MutableList<RequestsToTeam>> {
        lateinit var result: Result<MutableList<RequestsToTeam>>
        when (val response = teamsRepository.getTeamsRequests()) {
            is Result.Success -> {
                if (response.data != null) {
                    val createRequestItem = fun(request: RequestDto): RequestItem = RequestItem(
                        request.id,
                        request.user,
                        request.team,
                        request.type,
                        request.isCanceled
                    )

                    val requestsFromTeams = response.data.fromTeams.map(createRequestItem)
                    val requestsFromUsers = response.data.fromUsers.map(createRequestItem)

                    result = Result.Success(
                        mutableListOf(
                            RequestsToTeam(requestsFromTeams, RequestType.TeamToUser),
                            RequestsToTeam(requestsFromUsers, RequestType.UserToTeam)
                        )
                    )
                } else {
                    result = Result.Success(null)
                }
            }
            is Result.Error -> result = Result.Error(response.message!!)
        }
        return result
    }
}