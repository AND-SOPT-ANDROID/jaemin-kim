package org.sopt.and.myinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.components.EmptyInfoBox
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black100
import org.sopt.and.ui.theme.Grey100
import org.sopt.and.ui.theme.White100

@Composable
fun MyInfoScreen(
    modifier: Modifier = Modifier,
    myEmail: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Black100)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.27f)
                .background(color = Grey100)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = stringResource(id = R.string.my_account_icon_description),
                    modifier = Modifier.size(80.dp),
                    tint = White100
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = myEmail,
                    color = White100
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = stringResource(id = R.string.my_notification_icon_description),
                    modifier = Modifier.size(30.dp),
                    tint = White100
                )

                Spacer(modifier = Modifier.width(24.dp))

                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = stringResource(id = R.string.my_setting_icon_description),
                    modifier = Modifier.size(30.dp),
                    tint = White100
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = stringResource(id = R.string.my_first_payment_text),
                color = White100
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(id = R.string.my_to_payment_button),
                color = White100
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.13f)
                .background(color = Grey100)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.my_no_ticket_text),
                color = White100
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(id = R.string.my_to_payment_button),
                color = White100
            )
        }

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


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MyScreenPreview() {
    ANDANDROIDTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MyInfoScreen(
                modifier = Modifier.padding(innerPadding),
                myEmail = "내 이메일"
            )
        }
    }
}