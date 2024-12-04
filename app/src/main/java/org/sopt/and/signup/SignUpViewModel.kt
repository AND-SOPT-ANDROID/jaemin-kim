package org.sopt.and.signup

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import org.sopt.and.services.ServicePool
import org.sopt.and.signup.dto.SignUpRequestDto
import org.sopt.and.signup.dto.SignUpResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val userService by lazy { ServicePool.userService(application) }

    private val _signUpResultState = mutableStateOf<SignUpResponseDto?>(null)
    val signUpResultState: State<SignUpResponseDto?> get() = _signUpResultState

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _signUpResult = MutableLiveData<SignUpResult>()
    val signUpResult: LiveData<SignUpResult> = _signUpResult

    fun initSignUpResult() {
        _signUpResult.value = SignUpResult.Initial
    }

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

    fun signUp(
        signUpUsername: String,
        signUpPassword: String,
        signUpHobby: String
    ) {
        userService.signUp(
            request = SignUpRequestDto(
                username = signUpUsername,
                password = signUpPassword,
                hobby = signUpHobby
            )
        ).enqueue(
            object : Callback<SignUpResponseDto> {
                override fun onResponse(
                    call: Call<SignUpResponseDto>,
                    response: Response<SignUpResponseDto>
                ) {
                    if (response.isSuccessful) {
                        _signUpResultState.value = response.body()
                        _signUpResult.value = SignUpResult.Success
                    } else {
                        _signUpResultState.value = response.errorBody()?.string()
                            ?.let { Json.decodeFromString<SignUpResponseDto>(it) }
                        if (signUpResultState.value?.code == SignUpFailureCase.FAILURE_LENGTH.errorCode
                            && response.code() == SignUpFailureCase.FAILURE_LENGTH.statusCode
                        ) {
                            _signUpResult.value = SignUpResult.FailureInformationLength
                        } else if (signUpResultState.value?.code == SignUpFailureCase.FAILURE_DUPLICATE_USERNAME.errorCode
                            && response.code() == SignUpFailureCase.FAILURE_DUPLICATE_USERNAME.statusCode
                        ) {
                            _signUpResult.value = SignUpResult.FailureDuplicateUsername
                        }
                    }
                }

                override fun onFailure(call: Call<SignUpResponseDto>, t: Throwable) {}
            }
        )
    }
}

data class SignUpFailureCase(
    val statusCode: Int,
    val errorCode: String
) {
    companion object {
        val FAILURE_LENGTH = SignUpFailureCase(statusCode = 400, errorCode = "01")
        val FAILURE_DUPLICATE_USERNAME = SignUpFailureCase(statusCode = 409, errorCode = "00")
    }
}