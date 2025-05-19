package com.jeric.narutobook.data.remote.dto.tailbeast

data class TailedBeastDto(
    val id: Int,
    val images: List<String>?,
    val jutsu: List<String>?,
    val name: String,
    val natureType: List<String>?,
    val tools: List<String>?,
    val uniqueTraits: List<String>?,
)
