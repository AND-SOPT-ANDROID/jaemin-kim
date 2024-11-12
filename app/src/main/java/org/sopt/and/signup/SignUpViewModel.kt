package org.sopt.and.signup

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.WavveUtils

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _signUpResult = MutableLiveData<SignUpResult>()
    val signUpResult: LiveData<SignUpResult> = _signUpResult

    fun setSignUpEmail(signUpEmail: String) {
        _uiState.value = _uiState.value.copy(
            signUpEmail = signUpEmail
        )
    }

    fun setSignUpPassword(signUpPassword: String) {
        _uiState.value = _uiState.value.copy(
            signUpPassword = signUpPassword
        )
    }

    fun changeSignUpPasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isSignUpPasswordVisible = !_uiState.value.isSignUpPasswordVisible
        )
    }

    private fun validateSignUpPassword(signUpPassword: String): Boolean {
        if (signUpPassword.length !in WavveUtils.MIN_PASSWORD_LENGTH..WavveUtils.MAX_PASSWORD_LENGTH) return false

        val validateValues = listOf<Boolean>(
            signUpPassword.any { it.isLowerCase() },
            signUpPassword.any { it.isUpperCase() },
            signUpPassword.any { it.isDigit() },
            signUpPassword.any { !it.isLetterOrDigit() }
        )
        val isValidate = validateValues.count { it } >= 3

        return isValidate
    }

    private fun validateSignUpEmail(email: String): Boolean = PatternsCompat
        .EMAIL_ADDRESS
        .matcher(email)
        .matches()

    fun signUp(signUpEmail: String, signUpPassword: String) {
        viewModelScope.launch {
            val isEmailValid = validateSignUpEmail(signUpEmail)
            val isPasswordValid = validateSignUpPassword(signUpPassword)

            _signUpResult.value = when {
                isEmailValid && isPasswordValid -> {
                    SignUpResult.Success
                }

                !isEmailValid -> {
                    SignUpResult.FailureEmail
                }

                else -> {
                    SignUpResult.FailurePassword
                }
            }
        }
    }
}