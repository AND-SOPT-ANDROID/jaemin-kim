package org.sopt.and.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.sopt.and.Constants
import org.sopt.and.home.HomeScreen
import org.sopt.and.myinfo.MyInfoScreen
import org.sopt.and.search.SearchScreen
import org.sopt.and.signin.SignInScreen
import org.sopt.and.signup.SignUpScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
) {
    val navigationViewModel = viewModel<NavigationViewModel>()
    val navigationUiState by navigationViewModel.uiState.collectAsState()
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (navigationUiState.isBottomNavigationVisible) {
                WavveBottomNavigation(
                    items = Constants.wavveBottomNavigationItems,
                    navController,
                    navigationViewModel::setNavigationSelectedIndex,
                    navigationUiState.navigationSelectedIndex
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SignIn("", "") // 이녀석 생성자 안써서 3시간 날림
        ) {
            composable<Routes.SignIn> {
                SignInScreen(
                    navigateToSignUp = {
                        navController.navigate(Routes.SignUp)
                    },
                    navigateToMyInfo = { myEmail ->
                        navigationViewModel.changeSignInPasswordVisibility()
                        navController.navigate(
                            Routes.MyInfo(myEmail)
                        )
                    },
                    paddingValues = innerPadding
                )
            }

            composable<Routes.SignUp> {
                SignUpScreen(
                    navigateToSignIn = { signUpEmail, signUpPassword ->
                        navController.navigate(Routes.SignIn(signUpEmail, signUpPassword))
                    }
                )
            }

            composable<Routes.MyInfo> { backStackEntry ->
                val item = backStackEntry.toRoute<Routes.MyInfo>()
                MyInfoScreen(
                    myEmail = item.myEmail,
                    paddingValues = innerPadding
                )
            }

            composable<Routes.Home> {
                HomeScreen(
                    innerPadding = innerPadding
                )
            }

            composable<Routes.Search> {
                SearchScreen()
            }
        }
    }
}