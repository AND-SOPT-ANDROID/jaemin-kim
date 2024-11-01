package org.sopt.and.components

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import org.sopt.and.R
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100

@Composable
fun GreetingText(
    fontSize: Int,
    context: Context
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(
                    lineHeight = 2.em,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Top,
                        trim = LineHeightStyle.Trim.Both
                    )
                )
            ) {
                withStyle(
                    style = SpanStyle(
                        color = White100,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(getString(context, R.string.sign_up_focused_welcome_text_first_line))
                }

                withStyle(
                    style = SpanStyle(
                        color = Grey200,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(getString(context, R.string.sign_up_remainder_welcome_text_first_line))
                }

                withStyle(
                    style = SpanStyle(
                        color = White100,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(getString(context, R.string.sign_up_focused_welcome_text_second_line))
                }

                withStyle(
                    style = SpanStyle(
                        color = Grey200,
                        fontSize = fontSize.sp
                    )
                ) {
                    append(getString(context, R.string.sign_up_remainder_welcome_text_second_line))
                }
            }
        }
    )
}