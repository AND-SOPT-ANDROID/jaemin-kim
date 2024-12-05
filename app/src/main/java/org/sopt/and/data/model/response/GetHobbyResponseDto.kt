package org.sopt.and.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GetHobbyResponseDto(
    val result: GetHobbyResponseResultDto? = null,
    val code: String? = null
)

@Serializable
data class GetHobbyResponseResultDto(
    val hobby: String
)