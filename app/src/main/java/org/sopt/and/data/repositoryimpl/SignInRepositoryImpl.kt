package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.SignInDataSource
import org.sopt.and.data.mapper.Mapper
import org.sopt.and.domain.model.MyTokenEntity
import org.sopt.and.domain.model.SignInInformationEntity
import org.sopt.and.domain.repository.SignInRepository

class SignInRepositoryImpl(
    private val signInDataSource: SignInDataSource
) : SignInRepository {
    override suspend fun signIn(request: SignInInformationEntity): Result<MyTokenEntity> =
        runCatching {
            Mapper.toMyTokenEntity(
                signInDataSource.signIn(
                    Mapper.toSignInRequestDto(
                        request
                    )
                )
            )!!
        }
}