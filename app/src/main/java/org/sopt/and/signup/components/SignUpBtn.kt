package org.sopt.and.signup.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.WavveUtils
import org.sopt.and.signup.SignUpResult.FailureDuplicateUsername
import org.sopt.and.signup.SignUpResult.FailureInformationLength
import org.sopt.and.signup.SignUpResult.Success
import org.sopt.and.signup.SignUpViewModel
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100

@Composable
fun SignUpBtn(
    signUpUsername: String,
    signUpPassword: String,
    signUpHobby: String,
    onSignUpComplete: (String, String) -> Unit,
    signUpViewModel: SignUpViewModel
) {
    val signUpResult by signUpViewModel.signUpResult.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(signUpResult) {
        when (signUpResult) {
            is Success -> {
                onSignUpComplete(signUpUsername, signUpPassword)
                WavveUtils.showToast(
                    context = context,
                    message = R.string.sign_up_success
                )
            }

            is FailureDuplicateUsername -> {
                WavveUtils.showToast(
                    context = context,
                    message = R.string.sign_up_failed_duplicate_username
                )
            }

            is FailureInformationLength -> {
                WavveUtils.showToast(
                    context = context,
                    message = R.string.sign_up_failed_information_length
                )
            }

            else -> {}
        }
    }

    Button(
        onClick = {
            signUpViewModel.signUp(
                signUpUsername = signUpUsername,
                signUpPassword = signUpPassword,
                signUpHobby = signUpHobby
            )
//
//            when (signUpResult) {
//                is Success -> {
//                    onSignUpComplete(signUpUsername, signUpPassword)
//                    WavveUtils.showToast(
//                        context = context,
//                        message = R.string.sign_up_success
//                    )
//                }
//
//                is FailureDuplicateUsername -> {
//                    WavveUtils.showToast(
//                        context = context,
//                        message = R.string.sign_up_failed_duplicate_username
//                    )
//                }
//
//                is FailureInformationLength -> {
//                    WavveUtils.showToast(
//                        context = context,
//                        message = R.string.sign_up_failed_information_length
//                    )
//                }
//
//                else -> {}
//            }
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