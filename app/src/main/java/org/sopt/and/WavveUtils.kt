package org.sopt.and

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.sopt.and.navigation.Routes
import org.sopt.and.navigation.WavveBottomNavigationItem

object WavveUtils {
    const val MIN_PASSWORD_LENGTH = 8
    const val MAX_PASSWORD_LENGTH = 20

    const val MYINFO_SCREEN_INDEX = 2
    const val SEARCH_SCREEN_INDEX = 1
    const val HOME_SCREEN_INDEX = 0

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

    val linkableSNS = listOf<Pair<Int, Int>>(
        Pair(R.drawable.kakao_talk_icon, R.string.link_kakao_icon_description),
        Pair(R.drawable.t_world_icon, R.string.link_tworld_icon_description),
        Pair(R.drawable.naver_icon, R.string.link_naver_icon_description),
        Pair(R.drawable.facebook_icon, R.string.link_facebook_icon_description),
        Pair(R.drawable.apple_icon, R.string.link_apple_icon_description),
    )

    fun transformationPasswordVisual(isVisible: Boolean): VisualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
}