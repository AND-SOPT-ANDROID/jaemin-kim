package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.ui.theme.ANDANDROIDTheme

//
//𝟏. MyActivity를 만들고 레이아웃을 구현해주세요. (하단의 바텀네비는 구현하지 않아도 괜찮습니다.)
//
//𝟐. 닉네임 부분에는 회원가입 시 작성한 이메일이 뜨도록 구현해 주세요.

class MyActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    My(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun My(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .weight(0.3f)
                .background(color = Color(0xFF252525))
        ){

        }
        Column(modifier = Modifier.weight(0.1f)){}
        Column(modifier = Modifier.weight(0.3f)){}
        Column(modifier = Modifier.weight(0.3f)){}
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
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}