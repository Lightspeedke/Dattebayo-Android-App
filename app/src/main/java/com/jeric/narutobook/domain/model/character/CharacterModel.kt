package com.jeric.narutobook.domain.model.character


data class CharacterModel(
    val id: String,
    val name: String,
    val images: List<String>?,
    val family: Family?,
    val jutsu: List<String>?,
    val natureType: List<String>?,
    val personal: Personal?,
    val tools: List<String>?,
)

data class Family(
    val father: String?,
    val mother: String?,
    val brother: String?,
    val daughter: String?,
    val wife: String?
)

data class Personal(
    val birthdate: String?,
    val bloodType: String?,
    val sex: String?,
)

