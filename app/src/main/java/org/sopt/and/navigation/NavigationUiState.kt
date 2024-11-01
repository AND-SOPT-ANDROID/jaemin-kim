package org.sopt.and.navigation

import org.sopt.and.WavveUtils

data class NavigationUiState(
    val isBottomNavigationVisible: Boolean = false,
    val navigationSelectedIndex: Int = WavveUtils.MYINFO_SCREEN_INDEX
)