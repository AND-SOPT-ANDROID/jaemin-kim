package org.sopt.and.data.mapper

import org.sopt.and.data.model.response.GetMyHobbyResponseResultDto
import org.sopt.and.domain.model.MyHobbyEntity

object Mapper {
    fun toMyHobbyEntity(getHobbyResponseResultDto: GetMyHobbyResponseResultDto) =
        MyHobbyEntity(myHobby = getHobbyResponseResultDto.myHobby)
}