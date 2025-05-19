package com.jeric.narutobook.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeric.narutobook.data.local.entity.CharacterEntity
import com.jeric.narutobook.data.local.entity.ClanEntity

@Dao
interface ClanDao {
    @Query("SELECT * FROM clan_table")
    fun getAllClan(): List<ClanEntity>

    @Query("SELECT * FROM clan_table WHERE id=:clan")
    fun getSelectedClan(clan: Int): ClanEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addClan(heroes: List<ClanEntity>)

    @Query("DELETE FROM clan_table")
    suspend fun deleteClan()
}