package org.sopt.and

import kotlinx.serialization.Serializable


object Routes {
    @Serializable
    data class My(
        val myEmail: String
    )

    @Serializable
    data class SignIn(
        val myEmail: String,
        val myPassword: String
    )

    @Serializable
    object SignUp
}