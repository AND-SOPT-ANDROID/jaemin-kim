package org.sopt.and.signup

import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.and.WavveUtils

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

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

    fun validateSignUpPassword(signUpPassword: String): Boolean {
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

    fun validateSignUpEmail(email: String): Boolean = PatternsCompat
        .EMAIL_ADDRESS
        .matcher(email)
        .matches()
}