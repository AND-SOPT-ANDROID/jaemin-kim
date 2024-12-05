package org.sopt.and.presentation.signin

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.sopt.and.R
import org.sopt.and.WavveUtils.showSnackbar
import org.sopt.and.data.model.request.SignInRequestDto
import org.sopt.and.data.model.response.SignInResponseDto
import org.sopt.and.services.AppContext
import org.sopt.and.services.ServicePool
import org.sopt.and.services.TokenManager
import retrofit2.Response

class SignInViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }
    private val tokenManager = TokenManager(AppContext.get())

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    private val _signInResult = MutableStateFlow<SignInResult>(SignInResult.Initial)
    val signInResult: StateFlow<SignInResult> = _signInResult.asStateFlow()

    private val _signInResultState = MutableStateFlow(SignInResponseDto())
    private val signInResultState: StateFlow<SignInResponseDto> = _signInResultState.asStateFlow()

    private fun initSignInResult() {
        _signInResult.value = SignInResult.Initial
    }

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

    fun signIn(
        signInUsername: String,
        signInPassword: String
    ) {
        viewModelScope.launch {
            runCatching {
                userService.signIn(
                    request = SignInRequestDto(
                        username = signInUsername,
                        password = signInPassword
                    )
                )
            }.onSuccess { response: Response<SignInResponseDto> ->
                if (response.isSuccessful) {
                    _signInResultState.value = response.body()!!
                    _signInResult.value = SignInResult.Success
                    response.body()?.result?.token?.let { token ->
                        tokenManager.saveToken(token)
                    }
                } else {
                    _signInResultState.value = response.errorBody()?.string()
                        ?.let { Json.decodeFromString<SignInResponseDto>(it) }!!

                    if (signInResultState.value.code == SignInFailureCase.FAILURE_LENGTH.errorCode
                        && response.code() == SignInFailureCase.FAILURE_LENGTH.statusCode
                    ) {
                        _signInResult.value = SignInResult.FailurePasswordLength
                    } else if (signInResultState.value.code == SignInFailureCase.FAILURE_WRONG_PASSWORD.errorCode
                        && response.code() == SignInFailureCase.FAILURE_WRONG_PASSWORD.statusCode
                    ) {
                        _signInResult.value = SignInResult.FailureWrongPassword
                    }
                }
            }
        }
    }

    fun confirmLogin(
        snackbarHostState: SnackbarHostState,
        navigateToMyInfo: () -> Unit,
        context: Context,
        scope: CoroutineScope
    ) {
        when (signInResult.value) {
            is SignInResult.Success -> {
                context.showSnackbar(
                    scope = scope,
                    snackbarHostState = snackbarHostState,
                    message = R.string.sign_in_success_message,
                )
                navigateToMyInfo()
                initSignInResult()
            }

            is SignInResult.FailurePasswordLength -> {
                context.showSnackbar(
                    scope = scope,
                    snackbarHostState = snackbarHostState,
                    message = R.string.sign_in_failed_password_length,
                )
                initSignInResult()
            }

            is SignInResult.FailureWrongPassword -> {
                context.showSnackbar(
                    scope = scope,
                    snackbarHostState = snackbarHostState,
                    message = R.string.sign_in_failed_wrong_password,
                )
                initSignInResult()
            }

            else -> {}
        }
    }
}

data class SignInFailureCase(
    val statusCode: Int,
    val errorCode: String
) {
    companion object {
        val FAILURE_LENGTH = SignInFailureCase(statusCode = 400, errorCode = "01")
        val FAILURE_WRONG_PASSWORD = SignInFailureCase(statusCode = 403, errorCode = "01")
    }
}