package com.jeric.narutobook.data.remote.dto.akatsuki

data class AkatsukiDto(
    val family: AkatsukiFamilyDto?,
    val id: Int,
    val images: List<String>?,
    val jutsu: List<String>?,
    val name: String,
    val natureType: List<String>?,
    val personal: AkatsukiPersonalDto?,
    val tools: List<String>?,
)

data class AkatsukiFamilyDto(
    val father: String?,
    val mother: String?,
    val brother: String?,
    val daughter: String?,
    val wife: String?
)

data class AkatsukiPersonalDto(
    val birthdate: String?,
    val bloodType: String?,
    val sex: String?,
)