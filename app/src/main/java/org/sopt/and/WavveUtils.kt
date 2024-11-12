package org.sopt.and

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.content.ContextCompat.getString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object WavveUtils {
    const val MIN_PASSWORD_LENGTH = 8
    const val MAX_PASSWORD_LENGTH = 20

    const val MYINFO_SCREEN_INDEX = 2
    const val SEARCH_SCREEN_INDEX = 1
    const val HOME_SCREEN_INDEX = 0

    val linkableSNS = listOf<Pair<Int, Int>>(
        Pair(R.drawable.kakao_talk_icon, R.string.link_kakao_icon_description),
        Pair(R.drawable.t_world_icon, R.string.link_tworld_icon_description),
        Pair(R.drawable.naver_icon, R.string.link_naver_icon_description),
        Pair(R.drawable.facebook_icon, R.string.link_facebook_icon_description),
        Pair(R.drawable.apple_icon, R.string.link_apple_icon_description),
    )

    fun transformationPasswordVisual(isVisible: Boolean): VisualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    fun showToast(
        context: Context,
        @StringRes message: Int
    ) = Toast.makeText(
        context,
        context.getString(message),
        Toast.LENGTH_SHORT
    ).show()

    fun showSnackbar(
        scope: CoroutineScope,
        context: Context,
        snackbarHostState: SnackbarHostState,
        @StringRes message: Int
    ) = scope.launch {
        snackbarHostState.showSnackbar(
            message = getString(
                context,
                message
            )
        )
    }
}