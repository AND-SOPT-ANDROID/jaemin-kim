package org.sopt.and

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.ui.theme.*
import org.sopt.and.ui.theme.ANDANDROIDTheme


class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                    ) { email, password ->
                    }
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onSignUpComplete: (String, String) -> Unit,
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

            Greeting(24, context)

            Spacer(modifier = Modifier.height(20.dp))

            SignUpEmailField(
                email = signUpEmail,
                onEmailChange = signUpViewModel::setSignUpEmail
            )

            Spacer(modifier = Modifier.height(20.dp))

            SignUpPasswordField(
                signUpPassword = signUpPassword,
                onPasswordChange = signUpViewModel::setSignUpPassword,
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
            onSignUpComplete = onSignUpComplete,
            signUpViewModel
        )
    }
}

@Composable
fun SignUpEmailField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    Column {
        SignInOrSignUpTextField(
            emailOrPassword = email,
            onValueChange = onEmailChange,
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
    onPasswordChange: (String) -> Unit,
    isSignUpPasswordVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Column {
        SignInOrSignUpTextField(
            emailOrPassword = signUpPassword,
            onValueChange = onPasswordChange,
            placeholder = R.string.sign_up_password_placeholder,
            visualTransformation = transformationPasswordVisual(isSignUpPasswordVisible),
            trailingIcon = {
                ShowAndHideToggle(isSignUpPasswordVisible, onVisibilityChange)
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
fun Greeting(
    fontSize: Int,
    context: Context
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(
                    lineHeight = 2.em,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Top,
                        trim = LineHeightStyle.Trim.Both
                    )
                )
            ) {
                withStyle(
                    style = SpanStyle(
                        color = White100,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(getString(context, R.string.sign_up_focused_welcome_text_first_line))
                }

                withStyle(
                    style = SpanStyle(
                        color = Grey200,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(getString(context, R.string.sign_up_remainder_welcome_text_first_line))
                }

                withStyle(
                    style = SpanStyle(
                        color = White100,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(getString(context, R.string.sign_up_focused_welcome_text_second_line))
                }

                withStyle(
                    style = SpanStyle(
                        color = Grey200,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(getString(context, R.string.sign_up_remainder_welcome_text_second_line))
                }
            }
        }
    )
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
                onSignUpComplete = { email, password -> }
            )
        }
    }
}