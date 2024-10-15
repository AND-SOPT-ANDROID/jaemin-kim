package org.sopt.and

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.ui.theme.*
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    var myEmail: String? by mutableStateOf("")
    var myPassword: String? by mutableStateOf("")

    private val signUpLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                myEmail = result.data?.getStringExtra(Companion.EMAIL_KEY) ?: ""
                myPassword = result.data?.getStringExtra(Companion.PASSWORD_KEY) ?: ""
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            ANDANDROIDTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { innerPadding ->
                    SignInScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignUpClick = {
                            val intent = Intent(this, SignUpActivity::class.java)
                            signUpLauncher.launch(intent)
                        },
                        onLoginClick = { enteredEmail, enteredPassword ->
                            if (enteredEmail == myEmail && enteredPassword == myPassword) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(message = getString(R.string.sign_in_success_message))
                                }
                                val intent = Intent(this, MyActivity::class.java).apply {
                                    putExtra(Companion.MY_EMAIL_KEY, enteredEmail)
                                }
                                startActivity(intent)
                            } else {
                                scope.launch {
                                    snackbarHostState.showSnackbar(message = getString(R.string.sign_in_failed_message))
                                }
                            }
                        },
                        email = email,
                        onEmailChange = { newValue ->
                            email = newValue
                        },
                        password = password,
                        onPasswordChange = { newValue -> password = newValue },
                        isPasswordVisible = isPasswordVisible,
                        onVisibilityChange = { isPasswordVisible = !isPasswordVisible }
                    )
                }
            }
        }
    }
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onLoginClick: (String, String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Black100)
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

            SignInEMailField(
                email = email,
                onEmailChange = onEmailChange
            )

            Spacer(modifier = Modifier.height(5.dp))

            SignInPasswordField(
                password = password,
                onPasswordChange = onPasswordChange,
                isPasswordVisible = isPasswordVisible,
                onVisibilityChange = onVisibilityChange
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    onLoginClick(email, password)
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
                    modifier = Modifier.clickable { onSignUpClick() },
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
            }
        }
    }
}

@Composable
fun SignInEMailField(
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
                modifier = Modifier.padding(innerPadding),
                onSignUpClick = {},
                onLoginClick = { email, password -> },
                email = TODO(),
                onEmailChange = TODO(),
                password = TODO(),
                onPasswordChange = TODO(),
                isPasswordVisible = TODO(),
                onVisibilityChange = TODO(),
            )
        }
    }
}