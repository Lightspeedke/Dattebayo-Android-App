package com.jeric.narutobook.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jeric.narutobook.domain.model.remote_key.HeroRemoteKeys

@Dao
interface HeroRemoteKeysDao {

    @Query("SELECT * FROM heroRemoteKeys WHERE id = :heroId")
    suspend fun getRemoteKeys(heroId: String): HeroRemoteKeys?

    @Upsert
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKeys>)

    @Query("DELETE FROM heroRemoteKeys")
    suspend fun deleteAllRemoteKeys()

}