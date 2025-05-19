package com.jeric.narutobook.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeric.narutobook.util.Constants

@Entity(tableName = Constants.AKATSUKI_TABLE)
data class AkatsukiEntity(
    val family: AkatsukiFamilyEntity?,
    @PrimaryKey val id: Int,
    val images: List<String>?,
    val jutsu: List<String>?,
    val name: String,
    val natureType: List<String>?,
    val personal: AkatsukiPersonalEntity?,
    val tools: List<String>?,
)

data class AkatsukiFamilyEntity(
    val father: String?,
    val mother: String?,
    val brother: String?,
    val daughter: String?,
    val wife: String?
)

data class AkatsukiPersonalEntity(
    val birthdate: String?,
    val bloodType: String?,
    val sex: String?,
)