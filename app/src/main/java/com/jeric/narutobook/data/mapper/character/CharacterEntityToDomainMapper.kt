package com.jeric.narutobook.data.mapper.character

import com.jeric.narutobook.data.local.entity.CharacterEntity
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.jeric.narutobook.domain.model.character.Family
import com.jeric.narutobook.domain.model.character.Personal

fun CharacterEntity.characterEntityToDomain(): CharacterModel {
    return CharacterModel(
        id = this.id,
        name = this.name,
        images = this.images,
        family = this.entityFamilyToDomain(),
        jutsu = this.jutsu,
        natureType = this.natureType,
        personal = this.entityPersonalToDomain(),
        tools = this.tools,
    )
}


fun CharacterEntity.entityFamilyToDomain(): Family {
    return Family(
        father = this.family?.father,
        mother = this.family?.mother,
        brother = this.family?.brother,
        daughter = this.family?.daughter,
        wife = this.family?.wife
    )
}

fun CharacterEntity.entityPersonalToDomain(): Personal {
    return Personal(
        birthdate = this.personal.birthdate,
        bloodType = this.personal.bloodType,
        sex = this.personal.sex,
    )
}
