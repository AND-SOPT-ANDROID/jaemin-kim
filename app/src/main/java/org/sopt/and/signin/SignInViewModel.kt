package org.sopt.and.signin

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.sopt.and.services.ServicePool
import org.sopt.and.services.TokenManager
import org.sopt.and.signin.dto.SignInRequestDto
import org.sopt.and.signin.dto.SignInResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    private val userService by lazy { ServicePool.userService(application) }
    private val tokenManager = TokenManager(application)

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    private val _signInResult = MutableLiveData<SignInResult>()
    val signInResult: LiveData<SignInResult> = _signInResult

    private val _signInResultState = mutableStateOf<SignInResponseDto?>(null)
    val signInResultState: State<SignInResponseDto?> get() = _signInResultState

    fun initSignInResult() {
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
        userService.signIn(
            request = SignInRequestDto(
                username = signInUsername,
                password = signInPassword
            )
        ).enqueue(
            object : Callback<SignInResponseDto> {
                override fun onResponse(
                    call: Call<SignInResponseDto>,
                    response: Response<SignInResponseDto>
                ) {
                    if (response.isSuccessful) {
                        _signInResultState.value = response.body()
                        _signInResult.value = SignInResult.Success

                        response.body()?.result?.token?.let { token ->
                            viewModelScope.launch {
                                tokenManager.saveToken(token)
                            }
                        }
                    } else {
                        _signInResultState.value = response.errorBody()?.string()
                            ?.let { Json.decodeFromString<SignInResponseDto>(it) }

                        if (signInResultState.value?.code == "01" && response.code() == 400) {
                            _signInResult.value = SignInResult.FailurePasswordLength
                        } else if (signInResultState.value?.code == "01" && response.code() == 403) {
                            _signInResult.value = SignInResult.FailureWrongPassword
                        }
                    }
                }

                override fun onFailure(call: Call<SignInResponseDto>, t: Throwable) {}
            }
        )
    }
}