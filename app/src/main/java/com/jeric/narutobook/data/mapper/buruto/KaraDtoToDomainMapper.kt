package com.jeric.narutobook.data.mapper.buruto

import com.jeric.narutobook.data.remote.dto.buruto.KaraDto
import com.jeric.narutobook.data.remote.dto.buruto.KaraFamilyDto
import com.jeric.narutobook.data.remote.dto.buruto.KaraPersonalDto
import com.jeric.narutobook.domain.model.buruto.KaraFamilyModel
import com.jeric.narutobook.domain.model.buruto.KaraModel
import com.jeric.narutobook.domain.model.buruto.KaraPersonalModel

fun KaraDto.toDomainModel(): KaraModel {
    return KaraModel(
        id = this.id,
        name = this.name,
        images = this.images,
        family = this.family?.toFamilyModel(),
        jutsu = this.jutsu,
        natureType = this.natureType,
        personal = this.personal?.toPersonalModel(),
        tools = this.tools,
    )
}


fun KaraFamilyDto.toFamilyModel(): KaraFamilyModel {
    return KaraFamilyModel(father, mother, brother, daughter, wife)
}

fun KaraPersonalDto.toPersonalModel(): KaraPersonalModel {
    return KaraPersonalModel(
        birthdate = this.birthdate,
        bloodType = this.bloodType,
        sex = this.sex,
    )
}



