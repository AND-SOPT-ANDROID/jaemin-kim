package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var eMail by remember { mutableStateOf("") }
    var password by remember{mutableStateOf("")}
    var passwordVisibility by remember{mutableStateOf(false)}

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "뒤로가기",
                    modifier = Modifier
                        .size(48.dp),
                    tint = Color(0xFFFDFDFD)
                )
                Text(
                    text = "wavve",
                    color = Color(0xFFFDFDFD),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight(800)
                    )
                )
                Spacer(modifier = Modifier.size(48.dp))
            }
            Spacer(modifier = Modifier.height(60.dp))

            SignInEMailField(
                eMail = eMail,
                onEmailChange = { newEmail -> eMail = newEmail }
            )
            Spacer(modifier = Modifier.height(5.dp))

            SignInPasswordField(
                password = password,
                onPasswordChange = { newPassword -> password = newPassword },
                isVisible = passwordVisibility,
                onVisibilityChange = {passwordVisibility = !passwordVisibility}
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                ,
                onClick = {},
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4557F0),
                    contentColor = Color(0xFFFDFDFD)
                )
            ){
                Text(
                    text = "로그인"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(0.6f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "아이디 찾기",
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
                Text(
                    text = "|",
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
                Text(
                    text = "비밀번호 재설정",
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
                Text(
                    text = "|",
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
                Text(
                    text = "회원가입",
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
            }
        }

    }
}

@Composable // Email을 입력받는 TextField
fun SignInEMailField(
    eMail: String,
    onEmailChange: (String) -> Unit
){
    Column{
        TextField(
            value = eMail,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2f2f2f),
                focusedContainerColor = Color(0xFF2f2f2f)
            ),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = "이메일 주소 또는 아이디",
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        )
    }
}
@Composable // Password를 입력받는 TextField
fun SignInPasswordField(
    password : String,
    onPasswordChange: (String) -> Unit,
    isVisible : Boolean,
    onVisibilityChange : () -> Unit
){
    Column{
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2f2f2f),
                focusedContainerColor = Color(0xFF2f2f2f)

            ),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = "비밀번호",
                    color = Color(0xFFa8a8a8),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Text(
                    text = if (isVisible) "hide" else "show",
                    color = Color(0xFFfbfbfb),
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable(onClick = onVisibilityChange),
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
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