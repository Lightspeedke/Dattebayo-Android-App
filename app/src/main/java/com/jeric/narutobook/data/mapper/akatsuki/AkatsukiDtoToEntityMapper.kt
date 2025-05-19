package com.jeric.narutobook.data.mapper.akatsuki

import com.jeric.narutobook.data.local.entity.AkatsukiEntity
import com.jeric.narutobook.data.local.entity.AkatsukiFamilyEntity
import com.jeric.narutobook.data.local.entity.AkatsukiPersonalEntity
import com.jeric.narutobook.data.local.entity.CharacterEntity
import com.jeric.narutobook.data.mapper.character.toEntity
import com.jeric.narutobook.data.remote.dto.akatsuki.AkatsukiDto
import com.jeric.narutobook.data.remote.dto.akatsuki.AkatsukiFamilyDto
import com.jeric.narutobook.data.remote.dto.akatsuki.AkatsukiPersonalDto
import com.jeric.narutobook.data.remote.dto.character.CharacterDto

fun AkatsukiDto.toEntity(): AkatsukiEntity {
    return AkatsukiEntity(
        id = this.id,
        name = this.name,
        images = this.images,
        family = this.family?.toFamilyEntity(),
        jutsu = this.jutsu,
        natureType = this.natureType,
        personal = this.personal?.toPersonalEntity(),
        tools = this.tools,
    )
}


fun AkatsukiFamilyDto.toFamilyEntity(): AkatsukiFamilyEntity {
    return AkatsukiFamilyEntity(father, mother, brother, daughter, wife)
}

fun AkatsukiPersonalDto.toPersonalEntity(): AkatsukiPersonalEntity {
    return AkatsukiPersonalEntity(
        birthdate = this.birthdate,
        bloodType = this.bloodType,
        sex = this.sex,
    )
}


fun List<CharacterDto>.toEntityList(): List<CharacterEntity> {
    return this.map { it.toEntity() }
}



