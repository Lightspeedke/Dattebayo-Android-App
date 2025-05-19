package com.jeric.narutobook.domain.model.akatsuki

import androidx.compose.runtime.Immutable

@Immutable
data class AkatsukiModel(
    val family: FamilyModel?,
    val id: Int,
    val images: List<String>?,
    val jutsu: List<String>?,
    val name: String,
    val natureType: List<String>?,
    val personal: PersonalModel?,
    val tools: List<String>?,
)

data class FamilyModel(
    val father: String?,
    val mother: String?,
    val brother: String?,
    val daughter: String?,
    val wife: String?
)

data class PersonalModel(
    val birthdate: String?,
    val bloodType: String?,
    val sex: String?,
)