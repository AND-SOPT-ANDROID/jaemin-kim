package org.sopt.and

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import org.sopt.and.navigation.Routes
import org.sopt.and.navigation.WavveBottomNavigationItem


class Constants {
    companion object {
        const val MIN_PASSWORD_LENGTH = 8
        const val MAX_PASSWORD_LENGTH = 20

        val wavveBottomNavigationItems = listOf<WavveBottomNavigationItem>(
            WavveBottomNavigationItem(
                label = R.string.bottom_navigation_home_label,
                icon = Icons.Default.Home,
                route = Routes.Home,
                index = 0
            ),
            WavveBottomNavigationItem(
                label = R.string.bottom_navigation_search_label,
                icon = Icons.Default.Search,
                route = Routes.Search,
                index = 1
            ),
            WavveBottomNavigationItem(
                label = R.string.bottom_navigation_my_info_label,
                icon = Icons.Default.AccountCircle,
                route = Routes.MyInfo(""),
                index = 2
            )
        )
    }
}