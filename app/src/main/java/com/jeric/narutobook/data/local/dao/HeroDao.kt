package com.jeric.narutobook.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeric.narutobook.data.local.entity.CharacterEntity
import com.jeric.narutobook.domain.model.character.CharacterModel

@Dao
interface HeroDao {
    @Query("SELECT * FROM images_table")
    fun getAllHeroes(): PagingSource<Int, CharacterEntity>

    @Query("SELECT * FROM images_table WHERE id=:heroId")
    fun getSelectedHero(heroId: Int): CharacterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes: List<CharacterEntity>)

    @Query("DELETE FROM images_table")
    suspend fun deleteAllHeroes()
}