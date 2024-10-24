package org.sopt.and    // 패키지 선언이 중요한 이유

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import org.sopt.and.navigation.Navigation
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ANDANDROIDTheme {
                Navigation(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}