package org.sopt.and.myinfo.dto

import kotlinx.serialization.Serializable

data class GetHobbyResponseDto(
    val result: GetHobbyResponseResultDto? = null,
    val code: String? = null
)

@Serializable
data class GetHobbyResponseResultDto(
    val hobby: String
)