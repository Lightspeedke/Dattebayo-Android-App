package com.jeric.narutobook.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.APPEND
import androidx.paging.LoadType.PREPEND
import androidx.paging.LoadType.REFRESH
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jeric.narutobook.data.local.NarutoDatabase
import com.jeric.narutobook.data.local.entity.CharacterEntity
import com.jeric.narutobook.data.mapper.character.toEntityList
import com.jeric.narutobook.data.remote.NarutoApi
import com.jeric.narutobook.domain.model.remote_key.HeroRemoteKeys
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class CharacterRemoteMediator(
    private val api: NarutoApi,
    private val db: NarutoDatabase
): RemoteMediator<Int, CharacterEntity>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    private val heroDao = db.heroDao()
    private val heroRemoteKeysDao = db.heroRemoteKeysDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        try {
            val currentPage = when (loadType) {
                REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    val page = remoteKeys?.nextPage?.minus(1) ?: STARTING_PAGE_INDEX
                    Log.d("meme", "REFRESH: $remoteKeys")
                    page

                }
                PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    Log.d("meme", "remoteKeysPrev: $prevPage")
                    prevPage
                }
                APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    Log.d("meme", "remoteKeysNext: ${remoteKeys?.nextPage}")
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = api.getAllCharacters(page = currentPage)

            val endOfPaginationReached = response.characters.isEmpty()
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1
            db.withTransaction {
                if (loadType == REFRESH) {
                    heroDao.deleteAllHeroes()
                    heroRemoteKeysDao.deleteAllRemoteKeys()
                }
                val remoteKeys = response.characters.map { unsplashImageDto ->
                    HeroRemoteKeys(
                        id = unsplashImageDto.id.toString(),
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                heroRemoteKeysDao.addAllRemoteKeys(remoteKeys)
                heroDao.addHeroes(response.characters.toEntityList())
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }catch(e: IOException) {
            return MediatorResult.Error(e)
        } catch(e: HttpException) {
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterEntity>
    ): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeysDao.getRemoteKeys(heroId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CharacterEntity>
    ): HeroRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                heroRemoteKeysDao.getRemoteKeys(heroId = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CharacterEntity>
    ): HeroRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                heroRemoteKeysDao.getRemoteKeys(heroId = unsplashImage.id)
            }
    }


}