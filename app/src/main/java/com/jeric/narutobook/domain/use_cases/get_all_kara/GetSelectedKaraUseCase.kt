package com.jeric.narutobook.domain.use_cases.get_all_kara

import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.domain.model.buruto.KaraModel

class GetSelectedKaraUseCase(
    private val repository: RepositoryImpl
) {
    suspend operator fun invoke(id: Int): KaraModel {
        return repository.getSelectedKara(kara = id)
    }
}