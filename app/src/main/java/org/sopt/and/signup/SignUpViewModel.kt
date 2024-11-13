package org.sopt.and.signup

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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


class SignUpViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _signUpResultState = mutableStateOf<SignUpResponseDto?>(null)
    val signUpResultState: State<SignUpResponseDto?> get() = _signUpResultState

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

    //    fun signUp(
//        signUpUsername: String,
//        signUpPassword: String,
//        signUpHobby: String
//    ) {
//        viewModelScope.launch {
//            val isUsernameValid = validateUserInformation(signUpUsername)
//            val isPasswordValid = validateUserInformation(signUpPassword)
//            val isHobbyValid = validateUserInformation(signUpHobby)
//
//            _signUpResult.value = when {
//                !isUsernameValid -> {
//                    SignUpResult.FailureUsername
//                }
//
//                !isPasswordValid -> {
//                    SignUpResult.FailurePassword
//                }
//
//                !isHobbyValid -> {
//                    SignUpResult.FailureHobby
//                }
//
//                else -> {
//                    SignUpResult.Success
//                }
//            }
//        }
//    }
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
        ).enqueue(object : Callback<SignUpResponseDto> {
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
                    if (signUpResultState.value?.code == "01" && response.code() == 400) {
                        _signUpResult.value = SignUpResult.FailureInformationLength
                    } else if (signUpResultState.value?.code == "00" && response.code() == 409) {
                        _signUpResult.value = SignUpResult.FailureDuplicateUsername
                    }
                    Log.d("jaemin", "${response.code()} ${signUpResultState.value?.code}")
                    val error = response.message()
                    Log.e("error", error.toString())
                }
            }

            override fun onFailure(call: Call<SignUpResponseDto>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }
        })
    }
}