package org.sopt.and.signin

data class SignInUiState(
    val signInEmail: String = "",
    val signInPassword: String = "",
    val isSignInPasswordVisible: Boolean = false
)