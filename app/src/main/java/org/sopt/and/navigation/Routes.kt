package org.sopt.and.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data class MyInfo(
        val myUsername: String
    ) : Routes()

    @Serializable
    data class SignIn(
        val signUpUsername: String,
        val signUpPassword: String
    ) : Routes()

    @Serializable
    object SignUp : Routes()

    @Serializable
    object Home : Routes()

    @Serializable
    object Search : Routes()
}