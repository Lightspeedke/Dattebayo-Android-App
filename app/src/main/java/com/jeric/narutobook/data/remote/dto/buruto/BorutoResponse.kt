package com.jeric.narutobook.data.remote.dto.buruto

import com.google.gson.annotations.SerializedName

data class BorutoResponse(
    @SerializedName("currentPage") val currentPage: Int,
    @SerializedName("kara") val kara: List<KaraDto>,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("total") val total: Int
)