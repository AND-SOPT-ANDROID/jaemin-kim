package org.sopt.and.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.WavveUtils
import org.sopt.and.ui.theme.Grey100
import org.sopt.and.ui.theme.Grey200

@Composable
fun WavveBottomNavigation(
    items: List<WavveBottomNavigationItem>,
    navController: NavController,
    setNavigationSelectedScreenIndex: (Int) -> Unit,
    navigationSelectedScreenIndex: Int
) {
    NavigationBar(
        modifier = Modifier.height(60.dp),
        containerColor = Color.Black
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == navigationSelectedScreenIndex,
                onClick = {
                    setNavigationSelectedScreenIndex(index)
                    navController.navigate(
                        item.route,
                        navOptions = navOptions {
                            launchSingleTop
                        }
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.label),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = Grey200,
                    unselectedTextColor = Grey200,
                    disabledIconColor = Grey100,
                    disabledTextColor = Grey100
                )
            )
        }

    }
}

@Preview
@Composable
fun WavveBottomNavigationPreview() {
    WavveBottomNavigation(
        WavveUtils.wavveBottomNavigationItems,
        navController = rememberNavController(),
        setNavigationSelectedScreenIndex = TODO(),
        navigationSelectedScreenIndex = TODO(),
    )
}