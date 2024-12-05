package org.sopt.and.myinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.myinfo.dto.GetHobbyResponseDto
import org.sopt.and.services.ServicePool

class MyInfoViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _uiState = MutableStateFlow(MyInfoUiState())
    val uiState: StateFlow<MyInfoUiState> = _uiState.asStateFlow()

    private fun setMyHobby(myHobby: String) {
        _uiState.value = _uiState.value.copy(myHobby = myHobby)
    }

    fun getMyHobby() {
        viewModelScope.launch {
            runCatching {
                userService.getMyHobby()
            }.onSuccess { response: GetHobbyResponseDto ->
                response.result?.let { setMyHobby(it.hobby) }
            }.onFailure { }
        }
    }
}