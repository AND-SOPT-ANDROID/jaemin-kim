package org.sopt.and.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Coupon(modifier: Modifier = Modifier) {
    Text(
        text = "첫 결제 시 첫 달 100원!",
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0281ED), // Start color (0281ED)
                        Color(0xFF02B9B5)  // End color (02B9B5)
                    )
                ),
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            )
            .padding(vertical = 15.dp)
    )
}