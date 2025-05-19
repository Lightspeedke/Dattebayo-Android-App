package com.jeric.narutobook.data.repository

import androidx.paging.PagingData
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.domain.model.buruto.KaraModel
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.jeric.narutobook.domain.model.clan.ClanModel
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel
import com.jeric.narutobook.domain.repository.PreferenceManager
import com.jeric.narutobook.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore: PreferenceManager
) {
    fun getAllCharacter(): Flow<PagingData<CharacterModel>> {
        return remote.getAllHeroes()
    }

    fun searchHeroes(query: String): Flow<PagingData<CharacterModel>> {
        return remote.searchHeroes(query = query)
    }

    fun getAllTailBeast(): Flow<List<TailedBeastModel>> {
        return remote.getAllTailBeast()
    }

    fun getClan(): Flow<List<ClanModel>> {
        return remote.getAllClan()
    }

    suspend fun getSelectedClan(clan: Int): ClanModel {
        return remote.getSelectedClan(clan = clan)
    }

    fun getKara(): Flow<List<KaraModel>> {
        return remote.getAllKara()
    }

    suspend fun getSelectedKara(kara: Int): KaraModel {
        return remote.getSelectedKara(kara = kara)
    }

    fun getAkatsuki(): Flow<List<AkatsukiModel>> {
        return remote.getAkatsuki()
    }

    suspend fun getSelectedAkatsuki(clan: Int): AkatsukiModel {
        return remote.getAkatsuki(akatsukiModel = clan)
    }

    suspend fun getSelectedTailedBeast(heroId: Int): TailedBeastModel {
        return remote.getSelectedTailedBeast(beast = heroId)
    }

    suspend fun getSelectedHero(heroId: Int): CharacterModel {
        return remote.getSelectedCharacter(hero = heroId)
    }

    suspend fun getSelectedHeroOnline(heroId: Int): CharacterModel {
        return remote.getSelectedCharacterOnline(hero = heroId)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

}