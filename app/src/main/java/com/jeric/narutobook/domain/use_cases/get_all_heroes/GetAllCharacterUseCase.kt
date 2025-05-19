package com.jeric.narutobook.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.character.CharacterModel
import kotlinx.coroutines.flow.Flow

class GetAllCharacterUseCase(
    private val repository: RepositoryImpl
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>> {
        return repository.getAllCharacter()
    }
}