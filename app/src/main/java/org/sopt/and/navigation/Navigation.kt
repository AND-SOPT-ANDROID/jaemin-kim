package org.sopt.and.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.sopt.and.myinfo.MyInfoScreen
import org.sopt.and.signin.SignInScreen
import org.sopt.and.signup.SignUpScreen

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
                    navigateToSignUp = {
                        navController.navigate(Routes.SignUp)
                    },
                    navigateToMyInfo = { myEmail ->
                        navController.navigate(Routes.MyInfo(myEmail))
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
                    myEmail = item.myEmail
                )
            }
        }
    }
}