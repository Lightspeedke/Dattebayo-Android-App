package com.jeric.narutobook.data.mapper.buruto

import com.jeric.narutobook.data.local.entity.KaraEntity
import com.jeric.narutobook.data.local.entity.KaraFamilyEntity
import com.jeric.narutobook.data.local.entity.KaraPersonalEntity
import com.jeric.narutobook.domain.model.buruto.KaraFamilyModel
import com.jeric.narutobook.domain.model.buruto.KaraModel
import com.jeric.narutobook.domain.model.buruto.KaraPersonalModel

fun KaraEntity.characterEntityToDomain(): KaraModel {
    return KaraModel(
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


fun KaraFamilyEntity.entityFamilyToDomain(): KaraFamilyModel {
    return KaraFamilyModel(
        father = this.father,
        mother = this.mother,
        brother = this.brother,
        daughter = this.daughter,
        wife = this.wife
    )
}

fun KaraPersonalEntity.entityPersonalToDomain(): KaraPersonalModel {
    return KaraPersonalModel(
        birthdate = this.birthdate,
        bloodType = this.bloodType,
        sex = this.sex,
    )
}
