
package com.jeric.narutobook

import android.content.Context
import androidx.paging.*
import androidx.paging.RemoteMediator.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jeric.narutobook.data.local.NarutoDatabase
import com.jeric.narutobook.data.local.entity.CharacterEntity
import com.jeric.narutobook.data.paging_source.CharacterRemoteMediator
import com.jeric.narutobook.utils.FakeRepository
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class PagingFlowTest {

    private lateinit var narutoRequestApi: FakeRepository
    private lateinit var narutoDatabase: NarutoDatabase
    private lateinit var context : Context
    @Before
    fun setup() {
        narutoRequestApi = FakeRepository()
        context = ApplicationProvider.getApplicationContext()
        narutoDatabase = Room.databaseBuilder(
            context,
            NarutoDatabase::class.java,
            "naruto_db"
        ).build()
    }

    @After
    fun cleanup() {
        narutoDatabase.clearAllTables()
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsWhenMoreDataIsPresent() =
        runBlocking {
            val remoteMediator = CharacterRemoteMediator(
                api = narutoRequestApi,
                db = narutoDatabase
            )
            val pagingState = PagingState<Int, CharacterEntity>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Success)
            assertFalse((result as MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadEndOfPaginationTrueWhenNoMoreData() =
        runBlocking {
            narutoRequestApi.clearData()
            val remoteMediator = CharacterRemoteMediator(
                api = narutoRequestApi,
                db = narutoDatabase
            )
            val pagingState = PagingState<Int, CharacterEntity>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Success)
            assertTrue((result as MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadErrorResultWhenErrorOccurs() =
        runBlocking {
            narutoRequestApi.addException()
            val remoteMediator = CharacterRemoteMediator(
                api = narutoRequestApi,
                db = narutoDatabase
            )
            val pagingState = PagingState<Int, CharacterEntity>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Error)
        }

}