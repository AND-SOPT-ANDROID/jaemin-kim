package org.sopt.and.signup.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    val result: SignUpResponseResultDto? = null,
    val code: String? = null
)

@Serializable
data class SignUpResponseResultDto(
    val no: Int
)