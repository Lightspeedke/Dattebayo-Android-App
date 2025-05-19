package com.jeric.narutobook.data.mapper.tailbeast

import com.jeric.narutobook.data.remote.dto.tailbeast.TailedBeastDto
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel

fun TailedBeastDto.toDomainModel(): TailedBeastModel {
    return TailedBeastModel(
        id = this.id,
        name = this.name,
        images = this.images,
        jutsu = this.jutsu,
        natureType = this.natureType,
        tools = this.tools,
        uniqueTraits = this.uniqueTraits
    )
}



