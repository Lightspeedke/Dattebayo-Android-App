package com.jeric.narutobook.domain.use_cases.get_all_tailbeast

import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel

class GetSelectedTailedBeastUseCase(
    private val repository: RepositoryImpl
) {
    suspend operator fun invoke(heroId: Int): TailedBeastModel {
        return repository.getSelectedTailedBeast(heroId = heroId)
    }
}