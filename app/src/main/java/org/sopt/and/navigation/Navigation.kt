package org.sopt.and.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.home.HomeScreen
import org.sopt.and.myinfo.MyInfoScreen
import org.sopt.and.myinfo.MyInfoViewModel
import org.sopt.and.search.SearchScreen
import org.sopt.and.signin.SignInScreen
import org.sopt.and.signup.SignUpScreen

@Composable
fun Navigation(
) {
    val navigationViewModel = viewModel<NavigationViewModel>()
    val navigationUiState by navigationViewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    val myInfoViewModel = viewModel<MyInfoViewModel>()
    val myInfoUiState by myInfoViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (navigationUiState.isBottomNavigationVisible) {
                WavveBottomNavigation(
                    items = navigationUiState.wavveBottomNavigationItems,
                    navController,
                    navigationViewModel::setNavigationSelectedIndex,
                    navigationUiState.navigationSelectedIndex
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SignIn // 이녀석 생성자 안써서 3시간 날림
        ) {
            composable<Routes.SignIn> {
                SignInScreen(
                    navigateToSignUp = { navController.navigate(route = Routes.SignUp) },
                    navigateToMyInfo = {
                        navigationViewModel.changeBottomNavigationVisibility()
                        navController.navigate(Routes.MyInfo)
                    }
                )
            }

            composable<Routes.SignUp> {
                SignUpScreen(
                    navigateToSignIn = {
                        navController.navigate(
                            route = Routes.SignIn,
                            navOptions = navOptions {
                                popUpTo<Routes.SignIn> {
                                    inclusive = true
                                }
                            }
                        )
                    }
                )
            }

            composable<Routes.MyInfo> {
                MyInfoScreen(
                    paddingValues = innerPadding,
                    myInfoViewModel = myInfoViewModel,
                    myInfoUiState = myInfoUiState
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