package com.jeric.narutobook.domain.use_cases.get_all_akatsuki

import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import kotlinx.coroutines.flow.Flow

class GetAllAkatsukiUseCase(
    private val repository: RepositoryImpl
) {
    operator fun invoke(): Flow<List<AkatsukiModel>> {
        return repository.getAkatsuki()
    }
}