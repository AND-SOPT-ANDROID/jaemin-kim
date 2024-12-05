package org.sopt.and.domain.repository

import org.sopt.and.data.datasource.SignInDataSource
import org.sopt.and.data.repositoryimpl.SignInRepositoryImpl
import org.sopt.and.data.service.ServicePool
import org.sopt.and.domain.model.MyTokenEntity
import org.sopt.and.domain.model.SignInInformationEntity

interface SignInRepository {
    suspend fun signIn(request: SignInInformationEntity): Result<MyTokenEntity>

    companion object {
        fun create(): SignInRepositoryImpl {
            return SignInRepositoryImpl(
                SignInDataSource(
                    ServicePool.userService
                )
            )
        }
    }
}