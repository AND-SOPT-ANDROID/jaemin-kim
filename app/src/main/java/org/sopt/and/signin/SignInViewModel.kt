package org.sopt.and.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.navigation.Routes

class SignInViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    private val _signInResult = MutableLiveData<SignInResult>()
    val signInResult: LiveData<SignInResult> = _signInResult

    private val signUpAccount = savedStateHandle.toRoute<Routes.SignIn>()

    fun setSignInUsername(signInUsername: String) {
        _uiState.value = _uiState.value.copy(
            signInUsername = signInUsername
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
        signUpAccount.signUpUsername.isNotEmpty()
                && _uiState.value.signInUsername == signUpAccount.signUpUsername
                && _uiState.value.signInPassword == signUpAccount.signUpPassword

    fun login() {
        viewModelScope.launch {
            _signInResult.value =
                if (isLoginSuccess()) SignInResult.Success else SignInResult.Failure
        }
    }
}