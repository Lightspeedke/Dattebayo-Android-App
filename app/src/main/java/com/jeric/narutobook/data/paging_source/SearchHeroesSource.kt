package com.jeric.narutobook.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jeric.narutobook.data.local.entity.CharacterEntity
import com.jeric.narutobook.data.mapper.akatsuki.toEntityList
import com.jeric.narutobook.data.remote.NarutoApi
import java.lang.Exception

class SearchHeroesSource(
    private val borutoApi: NarutoApi,
    private val query: String
) : PagingSource<Int, CharacterEntity>() {

    private val initialPageNo = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        val currentPage = params.key ?: initialPageNo
        return try {
            val response = borutoApi.searchCharactersByName(
                name = query,
                page = currentPage,
                limit = 20
            )
            val characters = response.characters.toEntityList()

            LoadResult.Page(
                data = characters,
                prevKey = if (currentPage == initialPageNo) null else currentPage - 1,
                nextKey = if (characters.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}