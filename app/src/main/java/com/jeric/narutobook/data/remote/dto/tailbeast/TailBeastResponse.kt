package com.jeric.narutobook.data.remote.dto.tailbeast

import com.google.gson.annotations.SerializedName

data class TailBeastResponse(
    @SerializedName("currentPage") val currentPage: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("tailed-beasts") val tailedBeasts: List<TailedBeastDto>,
    @SerializedName("total") val total: Int
)