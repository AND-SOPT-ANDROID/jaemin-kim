package org.sopt.and.services

import org.sopt.and.signin.dto.SignInRequestDto
import org.sopt.and.signin.dto.SignInResponseDto
import org.sopt.and.signup.dto.SignUpRequestDto
import org.sopt.and.signup.dto.SignUpResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    fun signUp(@Body request: SignUpRequestDto): Call<SignUpResponseDto>

    @POST("/login")
    fun signIn(@Body request: SignInRequestDto): Call<SignInResponseDto>
}