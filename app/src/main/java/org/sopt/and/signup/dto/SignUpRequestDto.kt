package org.sopt.and.signup.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    val username: String,
    val password: String,
    val hobby: String
)
