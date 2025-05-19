package com.jeric.narutobook.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeric.narutobook.util.Constants

@Entity(tableName = Constants.KARA_TABLE)
data class KaraEntity(
    val family: KaraFamilyEntity?,
    @PrimaryKey val id: Int,
    val images: List<String>?,
    val jutsu: List<String>?,
    val name: String,
    val natureType: List<String>?,
    val personal: KaraPersonalEntity?,
    val tools: List<String>?,
)

data class KaraFamilyEntity(
    val father: String?,
    val mother: String?,
    val brother: String?,
    val daughter: String?,
    val wife: String?
)

data class KaraPersonalEntity(
    val birthdate: String?,
    val bloodType: String?,
    val sex: String?,
)