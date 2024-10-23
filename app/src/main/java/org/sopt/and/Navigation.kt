package org.sopt.and

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.SignInScreen
        ) {
            composable<Routes.SignInScreen> {
                SignInScreen(
                    onSignUpClick = {
                        navController.navigate(Routes.SignUpScreen)
                    },
                    onLoginClick = { a, b -> },
                    email = "",
                    onEmailChange = { newValue ->
                    },
                    password = "",
                    onPasswordChange = { newValue -> },
                    isPasswordVisible = false,
                    onVisibilityChange = {},
                    paddingValues = innerPadding
                )
            }
            composable<Routes.SignUpScreen> {
                SignUpScreen(
                    email = "",
                    onEmailChange = { newValue -> },
                    password = "",
                    onPasswordChange = { newvalue -> },
                    isPasswordVisible = false,
                    onVisibilityChange = {},
                    onSignUpComplete = { a, b -> }
                )
            }
            composable<Routes.MyScreen> {
                MyScreen(
                    myEmail = ""
                )
            }
        }
    }
}