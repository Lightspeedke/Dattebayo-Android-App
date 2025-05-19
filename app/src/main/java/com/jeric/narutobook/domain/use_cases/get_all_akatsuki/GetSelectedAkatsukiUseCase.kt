package com.jeric.narutobook.domain.use_cases.get_all_akatsuki

import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel

class GetSelectedAkatsukiUseCase(
    private val repository: RepositoryImpl
) {
    suspend operator fun invoke(id: Int): AkatsukiModel {
        return repository.getSelectedAkatsuki(clan = id)
    }
}