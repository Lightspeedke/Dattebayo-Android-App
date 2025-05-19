package com.jeric.narutobook.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.character.CharacterModel
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: RepositoryImpl
) {
    operator fun invoke(query: String): Flow<PagingData<CharacterModel>> {
        return repository.searchHeroes(query = query)
    }
}