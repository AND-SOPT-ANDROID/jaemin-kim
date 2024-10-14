package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme


class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myEmail = intent.getStringExtra("myEmail") ?: ""
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    My(
                        modifier = Modifier.padding(innerPadding),
                        myEmail
                    )
                }
            }
        }
    }
}

@Composable
fun My(
    modifier: Modifier = Modifier,
    myEmail: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.27f)
                .background(color = Color(0xFF252525))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = stringResource(id = R.string.my_screen_account_icon_description),
                    modifier = Modifier.size(80.dp),
                    tint = Color(0xFFF2F2F2)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = myEmail,
                    color = Color(0xFFF2F2F2)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = stringResource(id = R.string.my_screen_notification_icon_description),
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFFF2F2F2)
                )
                Spacer(modifier = Modifier.width(24.dp))
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = stringResource(id = R.string.my_screen_setting_icon_description),
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFFF2F2F2)
                )
            }
            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = stringResource(id=R.string.first_payment_text),
                color = Color(0xFF9A9A9A)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.to_payment_button),
                color = Color(0xFFF2F2F2)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.13f)
                .background(color = Color(0xFF252525))
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.no_ticket_text),
                color = Color(0xFF9A9A9A)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.to_payment_button),
                color = Color(0xFFF2F2F2)
            )
        }
        EmptyBox(
            stringResource(R.string.viewing_history_box_title),
            stringResource(R.string.viewing_history_box_empty_text),
            modifier = Modifier.weight(0.3f)
        )
        EmptyBox(stringResource(
            R.string.program_of_interest_box_title),
            stringResource(R.string.program_of_interest_empty_text),
            modifier = Modifier.weight(0.3f))
    }
}

@Composable
fun EmptyBox(
    title : String,
    description : String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            color = Color(0xFFF2F2F2),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(1000)
            )
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Sharp.Warning,
                    contentDescription = description,
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFF9A9A9A)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = description,
                    color = Color(0xFF9A9A9A)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MyPreview() {
    ANDANDROIDTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            My(
                modifier = Modifier.padding(innerPadding),
                myEmail = "내 이메일"
            )
        }
    }
}