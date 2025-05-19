package com.jeric.narutobook.domain.use_cases.search_heroes

import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.character.CharacterModel

class SearchHeroesOnlineUseCase(
    private val repository: RepositoryImpl
) {
    suspend operator fun invoke(id: Int): CharacterModel {
        return repository.getSelectedHeroOnline(id)
    }
}