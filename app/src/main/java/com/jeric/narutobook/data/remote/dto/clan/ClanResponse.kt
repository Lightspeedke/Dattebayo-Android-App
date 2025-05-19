package com.jeric.narutobook.data.remote.dto.clan

import com.google.gson.annotations.SerializedName

data class ClanResponse(
    @SerializedName("clans") val clans: List<ClanDto>,
    @SerializedName("currentPage") val currentPage: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("total") val total: Int
)