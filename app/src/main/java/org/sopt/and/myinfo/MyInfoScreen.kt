package org.sopt.and.myinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.components.EmptyInfoBox
import org.sopt.and.myinfo.components.MyInfoProfile
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black100
import org.sopt.and.ui.theme.Grey100
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100

@Composable
fun MyInfoScreen(
    paddingValues: PaddingValues,
    myEmail: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Black100)
            .padding(paddingValues)
    ) {
        MyInfoProfile(
            myEmail = myEmail,
            modifier = Modifier.weight(0.16f)
        )

        MyInfoPaymentInducementBox(
            paymentInducementText = stringResource(id = R.string.my_first_payment_text),
            modifier = Modifier.weight(0.12f)
        )

        Spacer(modifier = Modifier.height(2.dp))

        MyInfoPaymentInducementBox(
            paymentInducementText = stringResource(id = R.string.my_no_ticket_text),
            modifier = Modifier.weight(0.12f)
        )

        EmptyInfoBox(
            stringResource(R.string.my_viewing_history_box_title),
            stringResource(R.string.my_viewing_history_box_empty_text),
            modifier = Modifier.weight(0.3f)
        )

        EmptyInfoBox(
            stringResource(
                R.string.my_program_of_interest_box_title
            ),
            stringResource(R.string.my_program_of_interest_empty_text),
            modifier = Modifier.weight(0.3f)
        )
    }
}

@Composable
fun MyInfoPaymentInducementBox(
    paymentInducementText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Grey100)
            .padding(16.dp)
    ) {
        Text(
            text = paymentInducementText,
            color = Grey200
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = R.string.my_to_payment_button),
            color = White100
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MyScreenPreview() {
    ANDANDROIDTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MyInfoScreen(
                paddingValues = innerPadding,
                myEmail = ""
            )
        }
    }
}