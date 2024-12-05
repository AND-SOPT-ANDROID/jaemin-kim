package org.sopt.and.services

import org.sopt.and.myinfo.dto.GetHobbyResponseDto
import org.sopt.and.signin.dto.SignInRequestDto
import org.sopt.and.signin.dto.SignInResponseDto
import org.sopt.and.signup.dto.SignUpRequestDto
import org.sopt.and.signup.dto.SignUpResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun signUp(@Body request: SignUpRequestDto): Response<SignUpResponseDto>

    @POST("/login")
    suspend fun signIn(@Body request: SignInRequestDto): Response<SignInResponseDto>

    @GET("/user/my-hobby")
    suspend fun getMyHobby(): GetHobbyResponseDto
}