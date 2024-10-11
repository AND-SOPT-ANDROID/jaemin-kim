package org.sopt.and

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {

    lateinit var eMail: String
    lateinit var password: String
    private val signUpLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                eMail = result.data?.getStringExtra("email") ?: ""
                password = result.data?.getStringExtra("password") ?: ""
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
                    SignIn(
                        modifier = Modifier.padding(innerPadding),
                        onSignUpClick = {
                            val intent = Intent(this, SignUpActivity::class.java)
                            signUpLauncher.launch(intent)
                        },
                        onLoginClick = { enteredEmail, enteredPassword ->
                            if (enteredEmail == eMail && enteredPassword == password) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(message = getString(R.string.login_success_message))
                                }
                                val intent = Intent(this, MyActivity::class.java).apply {
                                    putExtra("myEmail", enteredEmail)
                                }
                                startActivity(intent)
                            } else {
                                scope.launch {
                                    snackbarHostState.showSnackbar(message = getString(R.string.login_failed_message))
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SignIn(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onLoginClick: (String, String) -> Unit
) {
    var eMail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
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
                    contentDescription = stringResource(id=R.string.sign_in_screen_back_icon_description),
                    modifier = Modifier
                        .size(48.dp),
                    tint = Color(0xFFFDFDFD)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = Color(0xFFFDFDFD),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight(800)
                    )
                )
                Spacer(modifier = Modifier.size(48.dp))
            }
            Spacer(modifier = Modifier.height(60.dp))

            SignInEMailField(
                eMail = eMail,
                onEmailChange = { newEmail -> eMail = newEmail }
            )
            Spacer(modifier = Modifier.height(5.dp))

            SignInPasswordField(
                password = password,
                onPasswordChange = { newPassword -> password = newPassword },
                isVisible = passwordVisibility,
                onVisibilityChange = { passwordVisibility = !passwordVisibility }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    onLoginClick(eMail, password)
                },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4557F0),
                    contentColor = Color(0xFFFDFDFD)
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
                    text = stringResource(id = R.string.to_find_id_button),
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.seperator),
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.to_reset_password_button),
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.seperator),
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.to_sign_up_button),
                    color = Color(0xFFa8a8a8),
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
                    text = stringResource(id = R.string.sign_in_email_placeholder),
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        )
    }
}

@Composable
fun SignInPasswordField(
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
                    text = stringResource(id = R.string.sign_in_password_placeholder),
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Text(
                    text = stringResource(id = if (isVisible) R.string.hide_password_button else R.string.show_password_button),
                    color = Color(0xFFfbfbfb),
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable(onClick = onVisibilityChange),
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SignInPreview() {
    ANDANDROIDTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SignIn(
                modifier = Modifier.padding(innerPadding),
                onSignUpClick = {},
                onLoginClick = { email, password -> }
            )
        }
    }
}