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
            startDestination = Routes.SignIn("", "") // 이녀석 생성자 안써서 3시간 날림
        ) {
            composable<Routes.SignIn> {
                SignInScreen(
                    onSignUpClick = {
                        navController.navigate(Routes.SignUp)
                    },
                    paddingValues = innerPadding
                )
            }
            composable<Routes.SignUp> {
                SignUpScreen(
                    email = "",
                    onEmailChange = { newValue -> },
                    password = "",
                    onPasswordChange = { newvalue -> },
                    isPasswordVisible = false,
                    onVisibilityChange = {},
                    onSignUpComplete = { a, b ->
                        navController.navigate(Routes.SignIn(a, b))
                    }
                )
            }
            composable<Routes.My> {
                MyScreen(
                    myEmail = ""
                )
            }
        }
    }
}