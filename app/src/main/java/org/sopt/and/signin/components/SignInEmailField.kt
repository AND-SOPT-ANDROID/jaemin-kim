package org.sopt.and.signin.components

import androidx.compose.runtime.Composable
import org.sopt.and.R
import org.sopt.and.components.SignInOrSignUpTextField

@Composable
fun SignInEmailField(
    signInEmail: String,
    onSignInEmailChange: (String) -> Unit
) {
    SignInOrSignUpTextField(
        emailOrPassword = signInEmail,
        onValueChange = onSignInEmailChange,
        placeholder = R.string.sign_in_email_placeholder
    )
}