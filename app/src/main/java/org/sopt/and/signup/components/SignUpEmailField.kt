package org.sopt.and.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.components.CautionBox
import org.sopt.and.components.SignInOrSignUpTextField

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