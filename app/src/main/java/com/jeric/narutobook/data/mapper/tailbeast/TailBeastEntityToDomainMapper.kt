package com.jeric.narutobook.data.mapper.tailbeast

import com.jeric.narutobook.data.local.entity.TailedBeastEntity
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel

fun TailedBeastEntity.tailBeastEntityToDomain(): TailedBeastModel {
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



