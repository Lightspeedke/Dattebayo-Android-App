package com.jeric.narutobook.repository

import androidx.paging.PagingData
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.domain.model.buruto.KaraModel
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.jeric.narutobook.domain.model.clan.ClanModel
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel
import com.jeric.narutobook.domain.repository.RemoteDataSource
import com.jeric.narutobook.utils.akatsukiList
import com.jeric.narutobook.utils.characterList
import com.jeric.narutobook.utils.clanList
import com.jeric.narutobook.utils.getTailedBeast
import com.jeric.narutobook.utils.karaList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeRepositoryImpl : RemoteDataSource {

    override fun getAllHeroes(): Flow<PagingData<CharacterModel>> {
        val characters = characterList()
        return flowOf(PagingData.from(characters))
    }

    override fun searchHeroes(query: String): Flow<PagingData<CharacterModel>> {
        TODO("Not yet implemented")
    }

    override fun getAllTailBeast() = flow {
        emit(getTailedBeast())
    }

    override fun getAllClan() = flow {
        emit(clanList())
    }

    override fun getAkatsuki() = flow {
        emit(akatsukiList())
    }

    override suspend fun getAkatsuki(akatsukiModel: Int): AkatsukiModel {
        return akatsukiList().find { it.id == akatsukiModel } ?: akatsukiList().first()
    }

    override fun getAllKara() = flow {
        emit(karaList())
    }

    override suspend fun getSelectedKara(kara: Int): KaraModel {
        return karaList().find { it.id == kara } ?: karaList().first()
    }

    override suspend fun getSelectedClan(clan: Int): ClanModel {
        return clanList().find { it.id == clan } ?: clanList().first()
    }

    override suspend fun getSelectedTailedBeast(beast: Int): TailedBeastModel {
        return getTailedBeast().find { it.id == beast } ?: getTailedBeast().first()
    }

    override suspend fun getSelectedCharacter(hero: Int): CharacterModel {
        return characterList().find { it.id.toInt() == hero } ?: characterList().first()
    }

    override suspend fun getSelectedCharacterOnline(hero: Int): CharacterModel {
        TODO("Not yet implemented")
    }
}