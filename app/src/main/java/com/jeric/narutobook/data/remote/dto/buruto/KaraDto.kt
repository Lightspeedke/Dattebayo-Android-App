package com.jeric.narutobook.data.remote.dto.buruto

data class KaraDto(
    val family: KaraFamilyDto?,
    val id: Int,
    val images: List<String>?,
    val jutsu: List<String>?,
    val name: String,
    val natureType: List<String>?,
    val personal: KaraPersonalDto?,
    val tools: List<String>?,
)

data class KaraFamilyDto(
    val father: String?,
    val mother: String?,
    val brother: String?,
    val daughter: String?,
    val wife: String?
)

data class KaraPersonalDto(
    val birthdate: String?,
    val bloodType: String?,
    val sex: String?,
)