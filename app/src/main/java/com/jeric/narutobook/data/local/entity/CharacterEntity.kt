package com.jeric.narutobook.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeric.narutobook.util.Constants

@Entity(tableName = Constants.NARUTO_TABLE)
data class CharacterEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val images: List<String>?,
    val family: FamilyEntity?,
    val jutsu: List<String>?,
    val natureType: List<String>?,
    val personal: PersonalEntity,
    val tools: List<String>?,
)

data class FamilyEntity(
    val father: String?,
    val mother: String?,
    val brother: String?,
    val daughter: String?,
    val wife: String?
)

data class PersonalEntity(
    val birthdate: String?,
    val bloodType: String?,
    val sex: String?,
)

data class RankEntity(
    val ninjaRank: String?,
    val ninjaRegistration: String?
)

