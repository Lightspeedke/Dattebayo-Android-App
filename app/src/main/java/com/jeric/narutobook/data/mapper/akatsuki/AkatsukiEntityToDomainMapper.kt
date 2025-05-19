package com.jeric.narutobook.data.mapper.akatsuki

import com.jeric.narutobook.data.local.entity.AkatsukiEntity
import com.jeric.narutobook.data.local.entity.AkatsukiFamilyEntity
import com.jeric.narutobook.data.local.entity.AkatsukiPersonalEntity
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.domain.model.akatsuki.FamilyModel
import com.jeric.narutobook.domain.model.akatsuki.PersonalModel

fun AkatsukiEntity.characterEntityToDomain(): AkatsukiModel {
    return AkatsukiModel(
        id = this.id,
        name = this.name,
        images = this.images,
        family = this.family?.entityFamilyToDomain(),
        jutsu = this.jutsu,
        natureType = this.natureType,
        personal = this.personal?.entityPersonalToDomain(),
        tools = this.tools,
    )
}


fun AkatsukiFamilyEntity.entityFamilyToDomain(): FamilyModel {
    return FamilyModel(
        father = this.father,
        mother = this.mother,
        brother = this.brother,
        daughter = this.daughter,
        wife = this.wife
    )
}

fun AkatsukiPersonalEntity.entityPersonalToDomain(): PersonalModel {
    return PersonalModel(
        birthdate = this.birthdate,
        bloodType = this.bloodType,
        sex = this.sex,
    )
}
