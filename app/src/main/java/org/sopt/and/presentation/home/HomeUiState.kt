package org.sopt.and.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.and.R

data class HomeUiState(
    @StringRes
    val genres: List<Int> = listOf<Int>(
        R.string.genre_new_classic,
        R.string.genre_drama,
        R.string.genre_entertainment,
        R.string.genre_movie,
        R.string.genre_animation,
        R.string.genre_foreign_country_series
    ),
    @DrawableRes
    val banners: List<Int> = listOf<Int>(
        R.drawable.banner_1,
        R.drawable.banner_2,
        R.drawable.banner_3,
        R.drawable.banner_4
    ),
    @DrawableRes
    val recommends: List<Int> = listOf<Int>(
        R.drawable.recommend_1,
        R.drawable.recommend_2,
        R.drawable.recommend_3,
        R.drawable.recommend_4,
        R.drawable.recommend_5,
        R.drawable.recommend_6,
    ),
    @DrawableRes
    val rankers: List<Int> = listOf<Int>(
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
)
