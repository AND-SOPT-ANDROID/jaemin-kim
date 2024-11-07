package org.sopt.and.signin.components

import androidx.compose.runtime.Composable
import org.sopt.and.R
import org.sopt.and.WavveUtils.transformationPasswordVisual
import org.sopt.and.components.ShowOrHideToggle
import org.sopt.and.components.SignInOrSignUpTextField

@Composable
fun SignInPasswordField(
    signInPassword: String,
    onSignInPasswordChange: (String) -> Unit,
    isSignInPasswordVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    SignInOrSignUpTextField(
        emailOrPassword = signInPassword,
        onValueChange = onSignInPasswordChange,
        placeholder = R.string.sign_in_password_placeholder,
        visualTransformation = transformationPasswordVisual(isSignInPasswordVisible),
        trailingIcon = {
            ShowOrHideToggle(isSignInPasswordVisible, onVisibilityChange)
        }
    )
}