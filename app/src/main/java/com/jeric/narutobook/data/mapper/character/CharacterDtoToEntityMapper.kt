package com.jeric.narutobook.data.mapper.character

import com.jeric.narutobook.data.local.entity.CharacterEntity
import com.jeric.narutobook.data.local.entity.FamilyEntity
import com.jeric.narutobook.data.local.entity.PersonalEntity
import com.jeric.narutobook.data.remote.dto.character.CharacterDto
import com.jeric.narutobook.data.remote.dto.character.FamilyDto
import com.jeric.narutobook.data.remote.dto.character.PersonalDto

fun CharacterDto.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = this.id.toString(),
        name = this.name,
        images = this.images,
        family = this.family?.toFamilyEntity(),
        jutsu = this.jutsu,
        natureType = this.natureType,
        personal = this.personal.toPersonalEntity(),
        tools = this.tools,
    )
}


fun FamilyDto.toFamilyEntity(): FamilyEntity {
    return FamilyEntity(father, mother, brother, daughter, wife)
}

fun PersonalDto.toPersonalEntity(): PersonalEntity {
    return PersonalEntity(
        birthdate = this.birthdate,
        bloodType = this.bloodType,
        sex = this.sex,
    )
}


fun List<CharacterDto>.toEntityList(): List<CharacterEntity> {
    return this.map { it.toEntity() }
}




