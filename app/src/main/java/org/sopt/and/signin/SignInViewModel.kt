package org.sopt.and.signin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.and.Routes

class SignInViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    val signUpAccount = savedStateHandle.toRoute<Routes.SignIn>()

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

    fun changeSignInPasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isSignInPasswordVisible = !_uiState.value.isSignInPasswordVisible
        )
    }

    fun isLoginSuccess(): Boolean =
        signUpAccount.signUpEmail.isNotEmpty()
                && _uiState.value.signInEmail == signUpAccount.signUpEmail
                && _uiState.value.signInPassword == signUpAccount.signUpPassword
}