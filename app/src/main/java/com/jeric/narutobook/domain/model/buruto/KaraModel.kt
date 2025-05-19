package com.jeric.narutobook.domain.model.buruto

import androidx.compose.runtime.Immutable

@Immutable
data class KaraModel(
    val family: KaraFamilyModel?,
    val id: Int,
    val images: List<String>?,
    val jutsu: List<String>?,
    val name: String,
    val natureType: List<String>?,
    val personal: KaraPersonalModel?,
    val tools: List<String>?,
)

data class KaraFamilyModel(
    val father: String?,
    val mother: String?,
    val brother: String?,
    val daughter: String?,
    val wife: String?
)

data class KaraPersonalModel(
    val birthdate: String?,
    val bloodType: String?,
    val sex: String?,
)