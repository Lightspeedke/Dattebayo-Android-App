package com.jeric.narutobook.domain.model.tail_beast

import androidx.compose.runtime.Immutable

@Immutable
data class TailedBeastModel(
    val id: Int,
    val images: List<String>?,
    val jutsu: List<String>?,
    val name: String,
    val natureType: List<String>?,
    val tools: List<String>?,
    val uniqueTraits: List<String>?,
)

