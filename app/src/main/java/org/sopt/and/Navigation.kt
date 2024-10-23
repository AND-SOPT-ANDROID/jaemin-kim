package org.sopt.and

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.sopt.and.signin.SignInScreen

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
                        navController.navigate(Routes.My(myEmail))
                    },
                    paddingValues = innerPadding
                )
            }
            composable<Routes.SignUp> {
                SignUpScreen(
                    onSignUpComplete = { signUpEmail, signUpPassword ->
                        navController.navigate(Routes.SignIn(signUpEmail, signUpPassword))
                    }
                )
            }
            composable<Routes.My> { backStackEntry ->
                val item = backStackEntry.toRoute<Routes.My>()
                MyInfoScreen(
                    myEmail = item.myEmail
                )
            }
        }
    }
}