package com.jeric.narutobook.domain.use_cases.get_all_clan

import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.clan.ClanModel

class GetSelectedClanUseCase(
    private val repository: RepositoryImpl
) {
    suspend operator fun invoke(clan: Int): ClanModel {
        return repository.getSelectedClan(clan = clan)
    }
}