package org.sopt.and

import kotlinx.serialization.Serializable


object Routes {
    @Serializable
    data class My(
        val myEmail: String
    )

    @Serializable
    data class SignIn(
        val signUpEmail: String,
        val signUpPassword: String
    )

    @Serializable
    object SignUp
}