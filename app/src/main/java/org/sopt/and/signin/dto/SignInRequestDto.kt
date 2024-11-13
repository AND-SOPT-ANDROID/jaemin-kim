package org.sopt.and.signin.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    val username: String,
    val password: String
)
