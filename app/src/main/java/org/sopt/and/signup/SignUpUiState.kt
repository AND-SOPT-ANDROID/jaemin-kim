package org.sopt.and.signup

data class SignUpUiState(
    val signUpEmail: String = "",
    val signUpPassword: String = "",
    val isSignUpPasswordVisible: Boolean = false
)

sealed class SignUpResult {
    object Success : SignUpResult()
    object FailureEmail : SignUpResult()
    object FailurePassword : SignUpResult()
}