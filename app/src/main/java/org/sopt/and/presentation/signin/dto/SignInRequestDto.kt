package org.sopt.and.presentation.signin.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    val username: String,
    val password: String
)
