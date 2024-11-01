package org.sopt.and.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class WavveBottomNavigationItem(
    val label: Int,
    val icon: ImageVector,
    val route: Any,
    val index: Int
)