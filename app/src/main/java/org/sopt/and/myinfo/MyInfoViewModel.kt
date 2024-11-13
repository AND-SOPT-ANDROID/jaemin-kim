package org.sopt.and.myinfo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyInfoViewModel(
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyInfoUiState())
    val uiState: StateFlow<MyInfoUiState> = _uiState.asStateFlow()

    fun setMyUsername(username: String) {
        _uiState.value = _uiState.value.copy(myUsername = username)
    }
}