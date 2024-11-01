package org.sopt.and.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.components.LinkWithSNSBox
import org.sopt.and.signup.components.*
import org.sopt.and.ui.theme.*
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToSignIn: (String, String) -> Unit,
) {
    val context = LocalContext.current

    val signUpViewModel = viewModel<SignUpViewModel>()
    val signUpUiState by signUpViewModel.uiState.collectAsState()

    val signUpEmail = signUpUiState.signUpEmail
    val signUpPassword = signUpUiState.signUpPassword
    val isSignUpPasswordVisible = signUpUiState.isSignUpPasswordVisible

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Black100)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(16.dp)
        ) {
            SignUpTop()

            Spacer(modifier = Modifier.height(30.dp))

            SignUpGreetingText(24, context)

            Spacer(modifier = Modifier.height(20.dp))

            SignUpEmailField(
                email = signUpEmail,
                onSignUpEmailChange = signUpViewModel::setSignUpEmail
            )

            Spacer(modifier = Modifier.height(20.dp))

            SignUpPasswordField(
                signUpPassword = signUpPassword,
                onSignUpPasswordChange = signUpViewModel::setSignUpPassword,
                isSignUpPasswordVisible = isSignUpPasswordVisible,
                onVisibilityChange = signUpViewModel::changeSignUpPasswordVisibility
            )

            Spacer(modifier = Modifier.size(40.dp))

            LinkWithSNSBox(stringResource(R.string.sign_in_link_with_another_service_title))
        }

        SignUpBtn(
            signUpEmail = signUpEmail,
            signUpPassword = signUpPassword,
            context = context,
            onSignUpComplete = navigateToSignIn,
            signUpViewModel
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SignUpScreenPreview() {
    ANDANDROIDTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            SignUpScreen(
                modifier = Modifier
                    .padding(innerPadding),
                navigateToSignIn = { email, password -> }
            )
        }
    }
}