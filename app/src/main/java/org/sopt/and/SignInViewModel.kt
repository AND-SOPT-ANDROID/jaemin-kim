package org.sopt.and

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun setSignInEmail(signInEmail: String) {
        _uiState.value = _uiState.value.copy(
            signInEmail = signInEmail
        )
    }

    fun setSignInPassword(signInPassword: String) {
        _uiState.value = _uiState.value.copy(
            signInPassword = signInPassword
        )
    }

    fun changePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isPasswordVisible = !_uiState.value.isPasswordVisible
        )
    }

    fun isLoginSuccess(myEmail: String, myPassword: String): Boolean =
        myEmail.isNotEmpty() && _uiState.value.signInEmail == myEmail && _uiState.value.signInPassword == myPassword
}