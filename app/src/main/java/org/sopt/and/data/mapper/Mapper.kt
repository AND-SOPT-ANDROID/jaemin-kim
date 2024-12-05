package org.sopt.and.data.mapper

import org.sopt.and.data.model.request.SignInRequestDto
import org.sopt.and.data.model.request.SignUpRequestDto
import org.sopt.and.data.model.response.GetMyHobbyResponseResultDto
import org.sopt.and.data.model.response.SignInResponseDto
import org.sopt.and.data.model.response.SignUpResponseDto
import org.sopt.and.domain.model.MyHobbyEntity
import org.sopt.and.domain.model.MyNumberEntity
import org.sopt.and.domain.model.MyTokenEntity
import org.sopt.and.domain.model.SignInInformationEntity
import org.sopt.and.domain.model.SignUpInformationEntity
import retrofit2.Response

object Mapper {
    fun toMyHobbyEntity(getHobbyResponseResultDto: GetMyHobbyResponseResultDto) =
        MyHobbyEntity(myHobby = getHobbyResponseResultDto.myHobby)

    fun toMyNumberEntity(signUpResponseDto: SignUpResponseDto) =
        signUpResponseDto.result?.let { MyNumberEntity(no = it.no) }

    fun toMyTokenEntity(signInResponseDto: Response<SignInResponseDto>) =
        signInResponseDto.body()?.result?.let {
            MyTokenEntity(
                token = it.token,
                status = signInResponseDto.code(),
                code = signInResponseDto.body()!!.code
            )
        }

    fun toSignInInformationEntity(signInRequestDto: SignInRequestDto) =
        SignInInformationEntity(
            username = signInRequestDto.username,
            password = signInRequestDto.password
        )

    fun toSignUpInformationEntity(signUpRequestDto: SignUpRequestDto) = SignUpInformationEntity(
        username = signUpRequestDto.username,
        password = signUpRequestDto.password,
        hobby = signUpRequestDto.hobby
    )

    fun toSignInRequestDto(signInInformationEntity: SignInInformationEntity) = SignInRequestDto(
        username = signInInformationEntity.username,
        password = signInInformationEntity.password
    )

    fun toSignUpRequestDto(signUpInformationEntity: SignUpInformationEntity) = SignUpRequestDto(
        username = signUpInformationEntity.username,
        password = signUpInformationEntity.password,
        hobby = signUpInformationEntity.hobby
    )
}