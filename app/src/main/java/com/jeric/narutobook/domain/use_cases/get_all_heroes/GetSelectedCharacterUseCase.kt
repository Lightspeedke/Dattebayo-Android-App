package com.jeric.narutobook.domain.use_cases.get_all_heroes

import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.character.CharacterModel

class GetSelectedCharacterUseCase(
    private val repository: RepositoryImpl
) {
    suspend operator fun invoke(heroId: Int): CharacterModel {
        return repository.getSelectedHero(heroId = heroId)
    }
}