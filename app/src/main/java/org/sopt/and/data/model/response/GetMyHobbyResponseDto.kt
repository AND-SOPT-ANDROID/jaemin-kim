package org.sopt.and.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMyHobbyResponseDto(
    val result: GetMyHobbyResponseResultDto? = null,
    val code: String? = null
)

@Serializable
data class GetMyHobbyResponseResultDto(
    @SerialName("hobby")
    val myHobby: String
)