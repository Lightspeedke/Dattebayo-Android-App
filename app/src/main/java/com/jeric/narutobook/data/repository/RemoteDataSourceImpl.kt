package com.jeric.narutobook.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jeric.narutobook.data.local.NarutoDatabase
import com.jeric.narutobook.data.mapper.akatsuki.characterEntityToDomain
import com.jeric.narutobook.data.mapper.akatsuki.toEntity
import com.jeric.narutobook.data.mapper.buruto.characterEntityToDomain
import com.jeric.narutobook.data.mapper.buruto.toEntity
import com.jeric.narutobook.data.mapper.character.characterEntityToDomain
import com.jeric.narutobook.data.mapper.character.toDomainModel
import com.jeric.narutobook.data.mapper.clan.entityToDomain
import com.jeric.narutobook.data.mapper.clan.toEntity
import com.jeric.narutobook.data.mapper.tailbeast.tailBeastEntityToDomain
import com.jeric.narutobook.data.mapper.tailbeast.toEntity
import com.jeric.narutobook.data.paging_source.CharacterRemoteMediator
import com.jeric.narutobook.data.paging_source.SearchHeroesSource
import com.jeric.narutobook.data.remote.NarutoApi
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.domain.model.buruto.KaraModel
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.jeric.narutobook.domain.model.clan.ClanModel
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel
import com.jeric.narutobook.domain.repository.RemoteDataSource
import com.jeric.narutobook.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val narutoApi: NarutoApi,
    private val database: NarutoDatabase
) : RemoteDataSource {

    private val heroDao = database.heroDao()
    private val tailBeastDao = database.tailBeastDao()
    private val clanDao = database.clanDao()
    private val akatsukiDao = database.akatsukiDao()
    private val karaDao = database.karaDao()

    override fun getAllHeroes(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.SIZE),
            remoteMediator = CharacterRemoteMediator(narutoApi, database),
            pagingSourceFactory = { heroDao.getAllHeroes() }
        ).flow
            .map { pagingData ->
                pagingData.map { it.characterEntityToDomain() }
            }
    }

    override fun searchHeroes(query: String): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = 3),
            pagingSourceFactory = {
                SearchHeroesSource(borutoApi = narutoApi, query = query)
            }
        ).flow
            .map { pagingData ->
                pagingData.map { it.characterEntityToDomain() }
            }
    }


    override fun getAllTailBeast() = flow {
        try {
            val response = narutoApi.getAllTailBeast()
            val entities = response.tailedBeasts.map { it.toEntity() }
            tailBeastDao.addBeast(entities)
            val result = tailBeastDao.getTailBeast().map { it.tailBeastEntityToDomain() }
            emit(result)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllClan() = flow {
        try {
            val response = narutoApi.getClan()
            val entities = response.clans.map { it.toEntity() }
            clanDao.addClan(entities)
            val result = clanDao.getAllClan().map { it.entityToDomain() }
            emit(result)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    override fun getAkatsuki() = flow {
        try {
            val response = narutoApi.getAkatsuki()
            val entities = response.akatsuki.map { it.toEntity() }
            akatsukiDao.addAkatsuki(entities)
            val result = akatsukiDao.getAllAkatsuki().map { it.characterEntityToDomain() }
            emit(result)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAkatsuki(akatsukiModel: Int): AkatsukiModel {
        val entity = akatsukiDao.getSelectedAkatsuki(heroId = akatsukiModel)
        return entity.characterEntityToDomain()
    }

    override fun getAllKara() = flow {
        try {
            val response = narutoApi.getBoruto()
            val entities = response.kara.map { it.toEntity() }
            karaDao.addKara(entities)
            val result = karaDao.getAllKara().map { it.characterEntityToDomain() }
            emit(result)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getSelectedKara(kara: Int): KaraModel {
        val entity = karaDao.getSelectedKara(heroId = kara)
        return entity.characterEntityToDomain()
    }


    override suspend fun getSelectedClan(clan: Int): ClanModel {
        val entity = clanDao.getSelectedClan(clan = clan)
        return entity.entityToDomain()
    }

    override suspend fun getSelectedTailedBeast(beast: Int): TailedBeastModel {
        return tailBeastDao.getSelectedTailBeast(beast = beast)
    }

    override suspend fun getSelectedCharacter(hero: Int): CharacterModel {
        val entity = heroDao.getSelectedHero(heroId = hero)
        return entity.characterEntityToDomain()
    }

    override suspend fun getSelectedCharacterOnline(hero: Int): CharacterModel {
        val apiResponse = narutoApi.getCharacterById(hero.toString())
        return apiResponse.toDomainModel()
    }


}