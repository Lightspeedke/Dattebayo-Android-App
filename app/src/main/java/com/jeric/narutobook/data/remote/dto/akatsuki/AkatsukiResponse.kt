package com.jeric.narutobook.data.remote.dto.akatsuki

import com.google.gson.annotations.SerializedName

data class AkatsukiResponse(
    @SerializedName("akatsuki") val akatsuki: List<AkatsukiDto>,
    @SerializedName("currentPage") val currentPage: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("total") val total: Int
)