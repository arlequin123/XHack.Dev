package com.example.xhackdev.domain.usecases

import com.example.xhackdev.data.models.ProfileDto
import com.example.xhackdev.data.models.UserDetailsDto
import com.example.xhackdev.data.room.CurrentUserDao
import com.example.xhackdev.data.room.entities.CurrentUserEntity
import com.example.xhackdev.domain.repository.UserRepository
import com.example.xhackdev.utils.Result
import com.example.xhackdev.utils.toJsonFromObject
import com.google.gson.Gson

class GetProfileUseCase(private val userRepository: UserRepository, private val userDao: CurrentUserDao, private val gson: Gson) {
    suspend fun execute(): Result<ProfileDto> {
        val response = userRepository.getProfile()
        if(response is Result.Success){
            response.data?.let {
                val userEntity = CurrentUserEntity(it.id, if(it.avatarUrl.isNullOrEmpty()) "" else it.avatarUrl, it.name, it.email, it.description, it.specialization, gson.toJson(it.networks), gson.toJsonFromObject(
                    it.tags))
                userDao.updateUser(userEntity)
            }
        }

        return response
    }
}