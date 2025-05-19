package com.jeric.narutobook.data.mapper.buruto

import com.jeric.narutobook.data.local.entity.KaraEntity
import com.jeric.narutobook.data.local.entity.KaraFamilyEntity
import com.jeric.narutobook.data.local.entity.KaraPersonalEntity
import com.jeric.narutobook.data.remote.dto.buruto.KaraDto
import com.jeric.narutobook.data.remote.dto.buruto.KaraFamilyDto
import com.jeric.narutobook.data.remote.dto.buruto.KaraPersonalDto

fun KaraDto.toEntity(): KaraEntity {
    return KaraEntity(
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


fun KaraFamilyDto.toFamilyEntity(): KaraFamilyEntity {
    return KaraFamilyEntity(father, mother, brother, daughter, wife)
}

fun KaraPersonalDto.toPersonalEntity(): KaraPersonalEntity {
    return KaraPersonalEntity(
        birthdate = this.birthdate,
        bloodType = this.bloodType,
        sex = this.sex,
    )
}


fun List<KaraDto>.toEntityList(): List<KaraEntity> {
    return this.map { it.toEntity() }
}



