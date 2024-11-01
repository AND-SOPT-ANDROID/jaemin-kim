package org.sopt.and.signup.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.signup.SignUpViewModel
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100

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