package com.jeric.narutobook.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeric.narutobook.data.local.entity.TailedBeastEntity
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel

@Dao
interface TailBeastDao {


    @Query("SELECT * FROM tail_beast_table ORDER BY id DESC")
    fun getTailBeast(): List<TailedBeastEntity>

    @Query("SELECT * FROM tail_beast_table WHERE id=:beast")
    fun getSelectedTailBeast(beast: Int): TailedBeastModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBeast(heroes: List<TailedBeastEntity>)

    @Query("DELETE FROM tail_beast_table")
    suspend fun deleteBeast()


}