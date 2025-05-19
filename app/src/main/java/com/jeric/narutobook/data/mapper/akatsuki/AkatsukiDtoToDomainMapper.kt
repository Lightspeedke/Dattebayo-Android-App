package com.jeric.narutobook.data.mapper.akatsuki

import com.jeric.narutobook.data.remote.dto.akatsuki.AkatsukiDto
import com.jeric.narutobook.data.remote.dto.akatsuki.AkatsukiFamilyDto
import com.jeric.narutobook.data.remote.dto.akatsuki.AkatsukiPersonalDto
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.domain.model.akatsuki.FamilyModel
import com.jeric.narutobook.domain.model.akatsuki.PersonalModel

fun AkatsukiDto.toDomainModel(): AkatsukiModel {
    return AkatsukiModel(
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


fun AkatsukiFamilyDto.toFamilyModel(): FamilyModel {
    return FamilyModel(father, mother, brother, daughter, wife)
}

fun AkatsukiPersonalDto.toPersonalModel(): PersonalModel {
    return PersonalModel(
        birthdate = this.birthdate,
        bloodType = this.bloodType,
        sex = this.sex,
    )
}



