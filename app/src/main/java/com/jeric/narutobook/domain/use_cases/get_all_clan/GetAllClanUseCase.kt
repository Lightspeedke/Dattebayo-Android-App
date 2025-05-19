package com.jeric.narutobook.domain.use_cases.get_all_clan

import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.clan.ClanModel
import kotlinx.coroutines.flow.Flow

class GetAllClanUseCase(
    private val repository: RepositoryImpl
) {
    operator fun invoke(): Flow<List<ClanModel>> {
        return repository.getClan()
    }
}