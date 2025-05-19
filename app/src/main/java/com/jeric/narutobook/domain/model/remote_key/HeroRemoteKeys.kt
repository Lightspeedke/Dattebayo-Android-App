package com.jeric.narutobook.domain.model.remote_key

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "heroRemoteKeys")
data class HeroRemoteKeys(
    @PrimaryKey
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?,
)
