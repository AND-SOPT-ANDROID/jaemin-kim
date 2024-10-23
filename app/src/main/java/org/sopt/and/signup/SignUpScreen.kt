package org.sopt.and.signup

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.components.CautionBox
import org.sopt.and.components.GreetingText
import org.sopt.and.components.LinkWithSNSBox
import org.sopt.and.components.ShowOrHideToggle
import org.sopt.and.components.SignInOrSignUpTextField
import org.sopt.and.transformationPasswordVisual
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

            GreetingText(24, context)

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

@Composable
fun SignUpEmailField(
    email: String,
    onSignUpEmailChange: (String) -> Unit
) {
    Column {
        SignInOrSignUpTextField(
            emailOrPassword = email,
            onValueChange = onSignUpEmailChange,
            placeholder = R.string.sign_up_email_placeholder
        )

        Spacer(modifier = Modifier.height(10.dp))

        CautionBox(
            contentDescription = R.string.sign_up_email_description,
            caution = R.string.sign_up_email_caution
        )
    }
}

@Composable
fun SignUpPasswordField(
    signUpPassword: String,
    onSignUpPasswordChange: (String) -> Unit,
    isSignUpPasswordVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Column {
        SignInOrSignUpTextField(
            emailOrPassword = signUpPassword,
            onValueChange = onSignUpPasswordChange,
            placeholder = R.string.sign_up_password_placeholder,
            visualTransformation = transformationPasswordVisual(isSignUpPasswordVisible),
            trailingIcon = {
                ShowOrHideToggle(isSignUpPasswordVisible, onVisibilityChange)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        CautionBox(
            contentDescription = R.string.sign_up_password_description,
            caution = R.string.sign_up_password_caution
        )
    }
}

@Composable
fun SignUpTop() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier.width(36.dp)
        )

        Text(
            text = stringResource(id = R.string.sign_up_screen_title),
            color = White100,
            style = TextStyle(fontSize = 18.sp)
        )

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = stringResource(id = R.string.sign_up_close_description),
            tint = White100,
            modifier = Modifier.size(36.dp)
        )
    }
}

@Composable
fun SignUpBtn(
    signUpEmail: String,
    signUpPassword: String,
    context: Context,
    onSignUpComplete: (String, String) -> Unit,
    signUpViewModel: SignUpViewModel
) {
    Button(
        onClick = {
            val isEmailValid = signUpViewModel.validateSignUpEmail(signUpEmail)
            val isPasswordValid = signUpViewModel.validateSignUpPassword(signUpPassword)

            if (isEmailValid && isPasswordValid) {
                onSignUpComplete(signUpEmail, signUpPassword)
                Toast.makeText(
                    context,
                    context.getString(R.string.sign_up_success),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!isEmailValid) {
                Toast.makeText(
                    context,
                    context.getString(R.string.sign_up_failed_email),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.sign_up_failed_password),
                    Toast.LENGTH_SHORT
                ).show()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Grey200,
            contentColor = White100
        )
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_button),
            style = TextStyle(fontSize = 18.sp)
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