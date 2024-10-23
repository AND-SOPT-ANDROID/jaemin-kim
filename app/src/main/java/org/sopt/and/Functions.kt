package org.sopt.and

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

fun transformationPasswordVisual(isVisible: Boolean): VisualTransformation =
    if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

