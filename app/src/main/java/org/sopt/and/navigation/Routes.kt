package org.sopt.and.navigation

import kotlinx.serialization.Serializable


object Routes {
    @Serializable
    data class MyInfo(
        val myEmail: String
    )

    @Serializable
    data class SignIn(
        val signUpEmail: String,
        val signUpPassword: String
    )

    @Serializable
    object SignUp

    @Serializable
    object Home

    @Serializable
    object Search
}