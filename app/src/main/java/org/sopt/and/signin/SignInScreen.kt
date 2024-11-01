package org.sopt.and.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.components.LinkWithSNSBox
import org.sopt.and.signin.components.*
import org.sopt.and.ui.theme.*
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    navigateToMyInfo: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val signInViewModel = viewModel<SignInViewModel>()
    val signInUiState by signInViewModel.uiState.collectAsState()

    val signInEmail = signInUiState.signInEmail
    val signInPassword = signInUiState.signInPassword
    val isSignInPasswordVisible = signInUiState.isSignInPasswordVisible

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Black100)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignInTopBar()

                Spacer(modifier = Modifier.height(60.dp))

                SignInEmailField(
                    signInEmail = signInEmail,
                    onSignInEmailChange = signInViewModel::setSignInEmail
                )

                Spacer(modifier = Modifier.height(5.dp))

                SignInPasswordField(
                    signInPassword = signInPassword,
                    onSignInPasswordChange = signInViewModel::setSignInPassword,
                    isSignInPasswordVisible = isSignInPasswordVisible,
                    onVisibilityChange = signInViewModel::changeSignInPasswordVisibility
                )

                Spacer(modifier = Modifier.height(30.dp))

                SignInBtn(
                    isLoginSuccess = signInViewModel::isLoginSuccess,
                    scope = scope,
                    context = context,
                    snackbarHostState = snackbarHostState,
                    navigateToMyInfo = navigateToMyInfo,
                    signInEmail = signInUiState.signInEmail
                )

                Spacer(modifier = Modifier.height(20.dp))

                SignInToAdditionalFeatures(navigateToSignUp = navigateToSignUp)

                Spacer(modifier = Modifier.size(40.dp))

                LinkWithSNSBox(stringResource(R.string.sign_in_link_with_another_service_title))
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SignInScreenPreview() {
    ANDANDROIDTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            innerPadding
            SignInScreen(
                navigateToSignUp = {},
                navigateToMyInfo = { a -> }
            )
        }
    }
}