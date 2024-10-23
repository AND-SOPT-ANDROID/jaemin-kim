package org.sopt.and.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import org.sopt.and.LinkWithSNSBox
import org.sopt.and.R
import org.sopt.and.ShowAndHideToggle
import org.sopt.and.SignInOrSignUpTextField
import org.sopt.and.transformationPasswordVisual
import org.sopt.and.ui.theme.*
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navigateToSignUp: () -> Unit,
    navigateToMyInfo: (String) -> Unit,
    paddingValues: PaddingValues
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val signInViewModel = viewModel<SignInViewModel>()
    val signInUiState by signInViewModel.uiState.collectAsState()

    val email = signInUiState.signInEmail
    val password = signInUiState.signInPassword
    val isPasswordVisible = signInUiState.isSignInPasswordVisible

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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.sign_in_to_back_screen_description),
                        modifier = Modifier
                            .size(48.dp),
                        tint = White100
                    )

                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = White100,
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight(800)
                        )
                    )

                    Spacer(modifier = Modifier.size(48.dp))
                }

                Spacer(modifier = Modifier.height(60.dp))

                SignInEmailField(
                    email = email,
                    onEmailChange = signInViewModel::setSignInEmail
                )

                Spacer(modifier = Modifier.height(5.dp))

                SignInPasswordField(
                    password = password,
                    onPasswordChange = signInViewModel::setSignInPassword,
                    isPasswordVisible = isPasswordVisible,
                    onVisibilityChange = signInViewModel::changeSignInPasswordVisibility
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        if (signInViewModel.isLoginSuccess()) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = getString(
                                        context,
                                        R.string.sign_in_success_message
                                    )
                                )
                                navigateToMyInfo(signInUiState.signInEmail)
                            }
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = getString(
                                        context,
                                        R.string.sign_in_failed_message
                                    )
                                )
                            }
                        }
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue100,
                        contentColor = White100
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in_button)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in_to_find_id_button),
                        color = Grey200,
                        style = TextStyle(
                            fontSize = 11.sp
                        )
                    )

                    Text(
                        text = stringResource(id = R.string.seperator),
                        color = Grey200,
                        style = TextStyle(
                            fontSize = 11.sp
                        )
                    )

                    Text(
                        text = stringResource(id = R.string.sign_in_to_reset_password_button),
                        color = Grey200,
                        style = TextStyle(
                            fontSize = 11.sp
                        )
                    )

                    Text(
                        text = stringResource(id = R.string.seperator),
                        color = Grey200,
                        style = TextStyle(
                            fontSize = 11.sp
                        )
                    )

                    Text(
                        text = stringResource(id = R.string.sign_in_to_sign_up_button),
                        color = Grey200,
                        modifier = Modifier.clickable { navigateToSignUp() },
                        style = TextStyle(
                            fontSize = 11.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.size(40.dp))

                LinkWithSNSBox(stringResource(R.string.sign_in_link_with_another_service_title))
            }
        }
    }
}


@Composable
fun SignInEmailField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    SignInOrSignUpTextField(
        emailOrPassword = email,
        onValueChange = onEmailChange,
        placeholder = R.string.sign_in_email_placeholder
    )
}

@Composable
fun SignInPasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    SignInOrSignUpTextField(
        emailOrPassword = password,
        onValueChange = onPasswordChange,
        placeholder = R.string.sign_in_password_placeholder,
        visualTransformation = transformationPasswordVisual(isPasswordVisible),
        trailingIcon = {
            ShowAndHideToggle(isPasswordVisible, onVisibilityChange)
        }
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SignInScreenPreview() {
    ANDANDROIDTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SignInScreen(
                navigateToSignUp = {},
                navigateToMyInfo = { a -> },
                paddingValues = innerPadding
            )
        }
    }
}