package org.sopt.and

data class SignInUiState(
    val signInEmail: String = "",
    val signInPassword: String = "",
    val isPasswordVisible: Boolean = false
)