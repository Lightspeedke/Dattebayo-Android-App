package com.jeric.narutobook.domain.repository

import androidx.paging.PagingData
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.domain.model.buruto.KaraModel
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.jeric.narutobook.domain.model.clan.ClanModel
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<CharacterModel>>
    fun searchHeroes(query: String): Flow<PagingData<CharacterModel>>
    fun getAllTailBeast(): Flow<List<TailedBeastModel>>
    fun getAllClan(): Flow<List<ClanModel>>
    fun getAkatsuki(): Flow<List<AkatsukiModel>>
    fun getAllKara(): Flow<List<KaraModel>>
    suspend fun getSelectedKara(kara: Int): KaraModel
    suspend fun getAkatsuki(akatsukiModel: Int): AkatsukiModel
    suspend fun getSelectedClan(clan: Int): ClanModel
    suspend fun getSelectedTailedBeast(beast: Int): TailedBeastModel
    suspend fun getSelectedCharacter(hero: Int): CharacterModel
    suspend fun getSelectedCharacterOnline(hero: Int): CharacterModel

}