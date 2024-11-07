package org.sopt.and.signup.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100

@Composable
fun SignUpGreetingText(fontSize: Int) {
    Text(
        AnnotatedString(
            text = stringResource(R.string.sign_up_welcom_text),
            spanStyles = listOf(
                AnnotatedString.Range(
                    item = SpanStyle(
                        color = White100,
                        fontSize = fontSize.sp
                    ),
                    start = 0,
                    end = 9
                ),
                AnnotatedString.Range(
                    item = SpanStyle(
                        color = Grey200,
                        fontSize = fontSize.sp
                    ),
                    start = 9,
                    end = 12
                ),
                AnnotatedString.Range(
                    item = SpanStyle(
                        color = White100,
                        fontSize = fontSize.sp
                    ),
                    start = 13,
                    end = 24
                ),
                AnnotatedString.Range(
                    item = SpanStyle(
                        color = Grey200,
                        fontSize = fontSize.sp
                    ),
                    start = 24,
                    end = 29
                ),
            ),
            paragraphStyles = listOf(
                AnnotatedString.Range(
                    item = ParagraphStyle(
                        lineHeight = 2.em,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Top,
                            trim = LineHeightStyle.Trim.Both
                        )
                    ),
                    start = 0,
                    end = 29
                )
            )
        )
    )
}