package com.jeric.narutobook.domain.use_cases.get_all_kara

import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.domain.model.buruto.KaraModel
import kotlinx.coroutines.flow.Flow

class GetAllKaraUseCase(
    private val repository: RepositoryImpl
) {
    operator fun invoke(): Flow<List<KaraModel>> {
        return repository.getKara()
    }
}