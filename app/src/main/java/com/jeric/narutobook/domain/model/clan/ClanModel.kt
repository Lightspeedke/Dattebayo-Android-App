package com.jeric.narutobook.domain.model.clan

import androidx.compose.runtime.Immutable

@Immutable
data class ClanModel(
    val characters: List<Int>,
    val id: Int,
    val name: String
)

