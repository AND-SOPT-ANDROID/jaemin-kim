package org.sopt.and.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _signUpResult = MutableLiveData<SignUpResult>()
    val signUpResult: LiveData<SignUpResult> = _signUpResult

    fun setSignUpUsername(signUpUsername: String) {
        _uiState.value = _uiState.value.copy(
            signUpUsername = signUpUsername
        )
    }

    fun setSignUpPassword(signUpPassword: String) {
        _uiState.value = _uiState.value.copy(
            signUpPassword = signUpPassword
        )
    }

    fun setSignUpHobby(signUpHobby: String) {
        _uiState.value = _uiState.value.copy(
            signUpHobby = signUpHobby
        )
    }

    fun changeSignUpPasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isSignUpPasswordVisible = !_uiState.value.isSignUpPasswordVisible
        )
    }

    fun validateUserInformation(information: String): Boolean = information.length <= 8

    fun signUp(
        signUpUsername: String,
        signUpPassword: String,
        signUpHobby: String
    ) {
        viewModelScope.launch {
            val isUsernameValid = validateUserInformation(signUpUsername)
            val isPasswordValid = validateUserInformation(signUpPassword)
            val isHobbyValid = validateUserInformation(signUpHobby)

            _signUpResult.value = when {
                !isUsernameValid -> {
                    SignUpResult.FailureUsername
                }

                !isPasswordValid -> {
                    SignUpResult.FailurePassword
                }

                !isHobbyValid -> {
                    SignUpResult.FailureHobby
                }

                else -> {
                    SignUpResult.Success
                }
            }
        }
    }
}