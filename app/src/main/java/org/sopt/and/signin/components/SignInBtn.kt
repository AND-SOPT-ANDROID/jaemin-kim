package org.sopt.and.signin.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.R
import org.sopt.and.signin.SignInViewModel
import org.sopt.and.ui.theme.Blue100
import org.sopt.and.ui.theme.White100

@Composable
fun SignInBtn(
    snackbarHostState: SnackbarHostState,
    navigateToMyInfo: () -> Unit,
    signInViewModel: SignInViewModel
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val signInResult by signInViewModel.signInResult.collectAsStateWithLifecycle()

    LaunchedEffect(signInResult) {  // 이곳에 LaunchedEffect가 없으면 실행이 왜 안될까요?
        signInViewModel.confirmLogin(
            snackbarHostState = snackbarHostState,
            navigateToMyInfo = navigateToMyInfo,
            context = context,
            scope = scope
        )
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = {
            signInViewModel.signIn(
                signInUsername = signInViewModel.uiState.value.signInUsername,
                signInPassword = signInViewModel.uiState.value.signInPassword
            )
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
}