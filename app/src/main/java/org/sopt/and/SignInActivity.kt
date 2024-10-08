package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.ui.theme.ANDANDROIDTheme

//𝟏. SignInActivity를 만들고 레이아웃을 구현해주세요. (하단 또는 다른 서비스 계정으로 가입 부분은 구현하지 않으셔도 괜찮습니다.)
//
//𝟐. 회원가입 버튼을 누르면 회원가입 화면에서 회원 정보를 저장해서 로그인 화면으로 돌아오게 해주세요. (registerForActivityResult와 putExtra를 사용해보세요!)
//
//𝟑. 로그인이 성공했을 때와 실패했을 때 모두 상황에 맞는 Snackbar가 뜨도록 구현해주세요! (로그인 성공 조건 =  회원가입에서 받아온 ID, Password가 동일할 때)
//
//𝟒. 비밀번호는 기본적으로는 안 보이게 설정해주시고, show 버튼을 누를 경우에만 나타나도록 구현해주세요.

class SignInActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignIn(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SignIn(modifier: Modifier = Modifier) {

}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun SignInPreview() {
    ANDANDROIDTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SignIn(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}