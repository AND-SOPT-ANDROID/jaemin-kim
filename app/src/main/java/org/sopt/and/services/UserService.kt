package org.sopt.and.services

import org.sopt.and.signup.dto.SignUpRequestDto
import org.sopt.and.signup.dto.SignUpResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    fun signUp(@Body request: SignUpRequestDto): Call<SignUpResponseDto>
}