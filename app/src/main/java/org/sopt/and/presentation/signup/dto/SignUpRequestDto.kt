package org.sopt.and.presentation.signup.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    val username: String,
    val password: String,
    val hobby: String
)
