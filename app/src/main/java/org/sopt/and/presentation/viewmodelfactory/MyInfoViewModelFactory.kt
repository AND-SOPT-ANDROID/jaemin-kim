package org.sopt.and.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.and.domain.repository.GetMyHobbyRepository
import org.sopt.and.domain.usecase.GetMyHobbyUseCase
import org.sopt.and.presentation.myinfo.MyInfoViewModel

class MyInfoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {

            MyInfoViewModel::class.java -> {
                MyInfoViewModel(
                    GetMyHobbyUseCase(
                        getMyHobbyRepository = GetMyHobbyRepository.create()
                    )
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}