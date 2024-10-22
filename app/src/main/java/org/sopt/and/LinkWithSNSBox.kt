package org.sopt.and

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Grey200

@Composable
fun LinkWithSNSBox(
    title: String
) {
    Column {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Grey200
            )

            Spacer(modifier = Modifier.size(4.dp))

            Text(
                text = title,
                style = TextStyle(fontSize = 12.sp),
                color = Grey200
            )

            Spacer(modifier = Modifier.size(3.dp))

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Grey200
            )
        }

        Spacer(modifier = Modifier.size(24.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(R.drawable.kakao_talk_icon),
                contentDescription = stringResource(R.string.link_kakao_icon_description),
                tint = Color.Unspecified,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(42.dp)
            )
            Icon(
                painter = painterResource(R.drawable.t_world_icon),
                contentDescription = stringResource(R.string.link_tworld_icon_description),
                tint = Color.Unspecified,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(42.dp)
            )
            Icon(
                painter = painterResource(R.drawable.naver_icon),
                contentDescription = stringResource(R.string.link_naver_icon_description),
                tint = Color.Unspecified,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(42.dp)
            )
            Icon(
                painter = painterResource(R.drawable.facebook_icon),
                contentDescription = stringResource(R.string.link_facebook_icon_description),
                tint = Color.Unspecified,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(42.dp)
            )
            Icon(
                painter = painterResource(R.drawable.apple_icon),
                contentDescription = stringResource(R.string.link_apple_icon_description),
                tint = Color.Unspecified,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(42.dp)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(R.string.link_with_another_service_description),
            color = Grey200,
            style = TextStyle(
                fontSize = 10.sp
            )
        )
    }
}


@Preview(
    showBackground = true
)
@Composable
fun LinkWithSNSBoxPreview() {
    ANDANDROIDTheme {
        LinkWithSNSBox("굳 타이틀")
    }
}