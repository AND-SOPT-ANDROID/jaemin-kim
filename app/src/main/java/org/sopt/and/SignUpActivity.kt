package org.sopt.and

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.PatternsCompat
import org.sopt.and.ui.theme.ANDANDROIDTheme


class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SignUp(
                        modifier = Modifier.padding(innerPadding)
                    ) { email, password ->
                        val intent = Intent(this@SignUpActivity, SignInActivity::class.java).apply {
                            putExtra("email", email)
                            putExtra("password", password)
                        }
                        setResult(RESULT_OK, intent)
//                        startActivity(intent)   // 요녀석 수상하다
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun SignUp(
    modifier: Modifier = Modifier,
    onSignUpComplete: (String, String) -> Unit
) {
    var eMail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val context = LocalContext.current  // 이게 정확히 멀까

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
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
                eMail = eMail,
                onEmailChange = { newEmail -> eMail = newEmail }
            )

            Spacer(modifier = Modifier.height(20.dp))

            SignUpPasswordField(
                password = password,
                onPasswordChange = { newPassword -> password = newPassword },
                isVisible = passwordVisibility,
                onVisibilityChange = { passwordVisibility = !passwordVisibility }
            )
        }
        SignUpBtn(
            eMail = eMail,
            password = password,
            context = context,
            onSignUpComplete = onSignUpComplete
        )
    }

}

@Composable
fun SignUpEMailField(
    eMail: String,
    onEmailChange: (String) -> Unit
) {
    Column {
        TextField(
            value = eMail,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2f2f2f),
                focusedContainerColor = Color(0xFF2f2f2f)
            ),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sign_up_email_placeholder),
                    color = Color(0xFFa8a8a8)
                )
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = stringResource(id = R.string.sign_up_password_description),
                tint = Color(0xFFb6b6b6)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(id = R.string.sign_up_email_caution),
                color = Color(0xFFb6b6b6),
                style = TextStyle(fontSize = 11.sp)
            )
        }
    }
}

@Composable
fun SignUpPasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Column {
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2f2f2f),
                focusedContainerColor = Color(0xFF2f2f2f)
            ),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sign_up_password_placeholder),
                    color = Color(0xFFa8a8a8)
                )
            },
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Text(
                    text = stringResource(id = if (isVisible) R.string.hide_password_button else R.string.show_password_button),
                    color = Color(0xFFfbfbfb),
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable(onClick = onVisibilityChange)
                )
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = stringResource(id = R.string.sign_up_password_description),
                tint = Color(0xFFb6b6b6)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(id = R.string.sign_up_password_caution),
                color = Color(0xFFb6b6b6),
                style = TextStyle(fontSize = 11.sp)
            )
        }
    }
}

@Composable
fun Greeting(fontSize: Int) {
    Row {
        Text(
            text = stringResource(id = R.string.focused_welcome_text_first_line),
            color = Color(0xFFfbfbfb),
            style = TextStyle(fontSize = fontSize.sp)
        )
        Text(
            text = stringResource(id = R.string.remainder_welcome_text_first_line),
            color = Color(0xFF9f9f9f),
            style = TextStyle(fontSize = fontSize.sp)
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
    Row {
        Text(
            text = stringResource(id = R.string.focused_welcome_text_second_line),
            color = Color(0xFFfbfbfb),
            style = TextStyle(fontSize = fontSize.sp)
        )
        Text(
            text = stringResource(id = R.string.remainder_welcome_text_second_line),
            color = Color(0xFF9f9f9f),
            style = TextStyle(fontSize = fontSize.sp)
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
            color = Color(0xFFfbfbfb),
            style = TextStyle(fontSize = 18.sp)
        )
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = stringResource(id = R.string.close_sign_up_description),
            tint = Color(0xFFfbfbfb),
            modifier = Modifier.size(36.dp)
        )
    }
}

@Composable
fun SignUpBtn(
    eMail: String,
    password: String,
    context: Context,
    onSignUpComplete: (String, String) -> Unit
) {
    Button(
        onClick = {
            val isEmailValid = validateEmail(eMail)
            val isPasswordValid = validatePassword(password)

            if (isEmailValid && isPasswordValid) {
                onSignUpComplete(eMail, password)
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
            containerColor = Color(0xFF717171),
            contentColor = Color(0xFFfcfbfc)
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
fun SignUpPreview() {
    ANDANDROIDTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            SignUp(
                modifier = Modifier
                    .padding(innerPadding),
                onSignUpComplete = { email, password -> }
            )
        }
    }
}

// 이메일 양식을 확인하는 검증함수
fun validateEmail(email: String): Boolean {
    return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
}

// 일단 3가지를 만족시키는 정규식 4개를 다 만들고 이중 하나라도 만족하면 통과
fun validatePassword(password: String): Boolean {
    val passwordPatternExceptLower =
        """^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$""".toRegex()
    val passwordPatternExceptUpper =
        """^(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$""".toRegex()
    val passwordPatternExceptNumber =
        """^(?=.*[A-Z])(?=.*[a-z])(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$""".toRegex()
    val passwordPatternExceptSpecial =
        """^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d@$!%*?&]{8,20}$""".toRegex()
    return passwordPatternExceptLower.matches(password) ||
            passwordPatternExceptUpper.matches(password) ||
            passwordPatternExceptSpecial.matches(password) ||
            passwordPatternExceptNumber.matches(password)
}