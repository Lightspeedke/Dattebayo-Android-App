package com.jeric.narutobook.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeric.narutobook.util.Constants

@Entity(tableName = Constants.CLAN_TABLE)
data class ClanEntity(
    val characters: List<Int>,
    @PrimaryKey val id: Int,
    val name: String
)
