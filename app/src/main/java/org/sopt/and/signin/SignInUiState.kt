package org.sopt.and.signin

data class SignInUiState(
    val signInEmail: String = "",
    val signInPassword: String = "",
    val isSignInPasswordVisible: Boolean = false
)

sealed class SignInResult {
    object Success : SignInResult()
    object Failure : SignInResult()
}