package com.jeric.narutobook.data.mapper.clan

import com.jeric.narutobook.data.local.entity.ClanEntity
import com.jeric.narutobook.domain.model.clan.ClanModel

fun ClanEntity.entityToDomain(): ClanModel {
    return ClanModel(
        id = this.id,
        name = this.name,
        characters = this.characters
    )
}
