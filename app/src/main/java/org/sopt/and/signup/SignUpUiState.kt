package org.sopt.and.signup

data class SignUpUiState(
    val signUpEmail: String = "",
    val signUpPassword: String = "",
    val isSignUpPasswordVisible: Boolean = false
)