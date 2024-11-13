package org.sopt.and.myinfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.and.myinfo.dto.GetHobbyResponseDto
import org.sopt.and.services.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val userService by lazy { ServicePool.userService(application) }

    private val _uiState = MutableStateFlow(MyInfoUiState())
    val uiState: StateFlow<MyInfoUiState> = _uiState.asStateFlow()

    fun setMyHobby(myHobby: String) {
        _uiState.value = _uiState.value.copy(myHobby = myHobby)
    }

    fun getMyHobby() {
        userService.getMyHobby().enqueue(
            object : Callback<GetHobbyResponseDto> {
                override fun onResponse(
                    call: Call<GetHobbyResponseDto>,
                    response: Response<GetHobbyResponseDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.result?.hobby?.let { setMyHobby(it) }
                    } else {
                        setMyHobby("오류")
                    }
                }

                override fun onFailure(call: Call<GetHobbyResponseDto>, t: Throwable) {
                    // 어떤 처리를 할까요?
                }
            }
        )
    }
}