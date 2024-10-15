package org.sopt.and

import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import org.sopt.and.ui.theme.*
import org.sopt.and.ui.theme.ANDANDROIDTheme


class SignUpActivity : ComponentActivity() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                        email = email,
                        onEmailChange = { newEmail -> email = newEmail },
                        password = password,
                        onPasswordChange = { newPassword -> password = newPassword },
                        isPasswordVisible = isPasswordVisible,
                        onVisibilityChange = { isPasswordVisible = !isPasswordVisible }
                    ) { email, password ->
                        val intent = Intent(this@SignUpActivity, SignInActivity::class.java).apply {
                            putExtra(Companion.EMAIL_KEY, email)
                            putExtra(Companion.PASSWORD_KEY, password)
                        }
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onVisibilityChange: () -> Unit,
    onSignUpComplete: (String, String) -> Unit,
) {
    val context = LocalContext.current

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

            Greeting(24)

            Spacer(modifier = Modifier.height(20.dp))

            SignUpEMailField(
                email = email,
                onEmailChange = onEmailChange
            )

            Spacer(modifier = Modifier.height(20.dp))

            SignUpPasswordField(
                password = password,
                onPasswordChange = onPasswordChange,
                isPasswordVisible = isPasswordVisible,
                onVisibilityChange = onVisibilityChange
            )
        }

        SignUpBtn(
            email = email,
            password = password,
            context = context,
            onSignUpComplete = onSignUpComplete
        )
    }
}

@Composable
fun SignUpEMailField(
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
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Column {
        SignInOrSignUpTextField(
            emailOrPassword = password,
            onValueChange = onPasswordChange,
            placeholder = R.string.sign_up_password_placeholder,
            visualTransformation = transformationPasswordVisual(isPasswordVisible),
            trailingIcon = {
                ShowAndHideToggle(isPasswordVisible, onVisibilityChange)
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
fun Greeting(fontSize: Int) {
    val firstLineFocusedText = stringResource(id = R.string.sign_up_focused_welcome_text_first_line)
    val firstLineRemainderText = stringResource(id = R.string.sign_up_remainder_welcome_text_first_line)
    val secondLineFocusedText = stringResource(id = R.string.sign_up_focused_welcome_text_second_line)
    val secondLineRemainderText = stringResource(id = R.string.sign_up_remainder_welcome_text_second_line)

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
                    append(firstLineFocusedText)
                }

                withStyle(
                    style = SpanStyle(
                        color = Grey200,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(firstLineRemainderText)
                }

                withStyle(
                    style = SpanStyle(
                        color = White100,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(secondLineFocusedText)
                }

                withStyle(
                    style = SpanStyle(
                        color = Grey200,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(secondLineRemainderText)
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
    email: String,
    password: String,
    context: Context,
    onSignUpComplete: (String, String) -> Unit
) {
    Button(
        onClick = {
            val isEmailValid = validateEmail(email)
            val isPasswordValid = validatePassword(password)

            if (isEmailValid && isPasswordValid) {
                onSignUpComplete(email, password)
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
                onSignUpComplete = { email, password -> },
                email = "",
                onEmailChange = { TODO() },
                password = "",
                onPasswordChange = { },
                isPasswordVisible = false,
                onVisibilityChange = { }
            )
        }
    }
}