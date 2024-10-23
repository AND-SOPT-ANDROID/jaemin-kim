package org.sopt.and

data class SignUpUiState(
    val signUpEmail: String = "",
    val signUpPassword: String = "",
    val isSignUpPasswordVisible: Boolean = false
)