package org.sopt.and.domain.repository

import org.sopt.and.domain.model.MyHobbyEntity

interface GetMyHobbyRepository {
    suspend fun getMyHobby(): Result<MyHobbyEntity>
}