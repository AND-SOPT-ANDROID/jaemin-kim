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

        val genres = listOf<String>(
            "뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈"
        )

        val banners = listOf<Int>(
            R.drawable.banner_1,
            R.drawable.banner_2,
            R.drawable.banner_3,
            R.drawable.banner_4
        )

        val recommends = listOf<Int>(
            R.drawable.recommend_1,
            R.drawable.recommend_2,
            R.drawable.recommend_3,
            R.drawable.recommend_4,
            R.drawable.recommend_5,
            R.drawable.recommend_6,
        )

        val ranker = listOf<Int>(
            R.drawable.top_1,
            R.drawable.top_2,
            R.drawable.top_3,
            R.drawable.top_4,
            R.drawable.top_5,
            R.drawable.top_6,
            R.drawable.top_7,
            R.drawable.top_8,
            R.drawable.top_9,
            R.drawable.top_10
        )
    }
}