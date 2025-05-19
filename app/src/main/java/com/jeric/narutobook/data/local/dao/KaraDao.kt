package com.jeric.narutobook.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeric.narutobook.data.local.entity.AkatsukiEntity
import com.jeric.narutobook.data.local.entity.KaraEntity

@Dao
interface KaraDao {
    @Query("SELECT * FROM kara_table")
    fun getAllKara(): List<KaraEntity>

    @Query("SELECT * FROM kara_table WHERE id=:heroId")
    fun getSelectedKara(heroId: Int): KaraEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addKara(heroes: List<KaraEntity>)

    @Query("DELETE FROM kara_table")
    suspend fun deleteKara()
}