package com.jeric.narutobook.data.mapper.clan

import com.jeric.narutobook.data.remote.dto.clan.ClanDto
import com.jeric.narutobook.domain.model.clan.ClanModel

fun ClanDto.toDomainModel(): ClanModel {
    return ClanModel(
        id = this.id,
        name = this.name,
        characters = this.characters
    )
}
