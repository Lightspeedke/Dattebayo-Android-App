package com.jeric.narutobook.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeric.narutobook.data.local.entity.AkatsukiEntity

@Dao
interface AkatsukiDao {
    @Query("SELECT * FROM akatsuki_table")
    fun getAllAkatsuki(): List<AkatsukiEntity>

    @Query("SELECT * FROM akatsuki_table WHERE id=:heroId")
    fun getSelectedAkatsuki(heroId: Int): AkatsukiEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAkatsuki(heroes: List<AkatsukiEntity>)

    @Query("DELETE FROM akatsuki_table")
    suspend fun deleteAkatsuki()
}