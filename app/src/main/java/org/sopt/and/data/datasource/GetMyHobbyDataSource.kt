package org.sopt.and.data.datasource

import org.sopt.and.data.model.response.GetMyHobbyResponseDto
import org.sopt.and.data.service.UserService

class GetMyHobbyDataSource(
    private val userService: UserService
) {
    suspend fun getMyHobby(): GetMyHobbyResponseDto = userService.getMyHobby()
}