package com.jeric.narutobook.data.remote.dto.character

data class CharacterDto(
    val id: Int,
    val name: String,
    val images: List<String>?,
    val family: FamilyDto?,
    val jutsu: List<String>,
    val natureType: List<String>,
    val personal: PersonalDto,
    val tools: List<String>,
)

data class FamilyDto(
    val father: String?,
    val mother: String?,
    val brother: String?,
    val daughter: String?,
    val wife: String?
)

data class PersonalDto(
    val birthdate: String?,
    val bloodType: String?,
    val sex: String?,
)


