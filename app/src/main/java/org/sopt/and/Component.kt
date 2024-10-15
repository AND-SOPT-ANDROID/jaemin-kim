package org.sopt.and

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.PatternsCompat
import org.sopt.and.ui.theme.Grey100
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100

@Composable
fun SignInOrSignUpTextField(
    emailOrPassword: String,
    onValueChange: (String) -> Unit,
    placeholder: Int,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    TextField(
        value = emailOrPassword,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Grey100,
            focusedContainerColor = Grey100
        ),
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Text(
                text = stringResource(id = placeholder),
                color = Grey200
            )
        },
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon
    )
}

@Composable
fun ShowAndHideToggle(
    isVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Text(
        text = stringResource(id = if (isVisible) R.string.hide_password_button else R.string.show_password_button),
        color = White100,
        modifier = Modifier
            .padding(end = 12.dp)
            .clickable(onClick = onVisibilityChange)
    )
}

@Composable
fun CautionBox(
    caution: Int,
    contentDescription: Int
) {
    Row {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = stringResource(id = contentDescription),
            tint = Grey200
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = stringResource(id = caution),
            color = Grey200,
            style = TextStyle(fontSize = 11.sp)
        )
    }
}

fun transformationPasswordVisual(isVisible: Boolean): VisualTransformation =
    if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

fun validateEmail(email: String): Boolean = PatternsCompat
    .EMAIL_ADDRESS
    .matcher(email)
    .matches()

fun validatePassword(password: String): Boolean {
    if (password.length !in Companion.MIN_PASSWORD_LENGTH..Companion.MAX_PASSWORD_LENGTH) return false

    val validateValues = listOf<Boolean>(
        password.any { it.isLowerCase() },
        password.any { it.isUpperCase() },
        password.any { it.isDigit() },
        password.any { !it.isLetterOrDigit() }
    )
    val isValidate = validateValues.count { it } >= 3

    return isValidate
}