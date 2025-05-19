package com.jeric.narutobook.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeric.narutobook.data.local.converter.NarutoTypeConverters
import com.jeric.narutobook.data.local.dao.AkatsukiDao
import com.jeric.narutobook.data.local.dao.ClanDao
import com.jeric.narutobook.data.local.dao.HeroDao
import com.jeric.narutobook.data.local.dao.HeroRemoteKeysDao
import com.jeric.narutobook.data.local.dao.KaraDao
import com.jeric.narutobook.data.local.dao.TailBeastDao
import com.jeric.narutobook.data.local.entity.AkatsukiEntity
import com.jeric.narutobook.data.local.entity.CharacterEntity
import com.jeric.narutobook.data.local.entity.ClanEntity
import com.jeric.narutobook.data.local.entity.KaraEntity
import com.jeric.narutobook.data.local.entity.TailedBeastEntity
import com.jeric.narutobook.domain.model.remote_key.HeroRemoteKeys

@Database(
    entities = [
        CharacterEntity::class,
        TailedBeastEntity::class,
        HeroRemoteKeys::class,
        ClanEntity::class,
        AkatsukiEntity::class,
        KaraEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(NarutoTypeConverters::class)
abstract class NarutoDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun tailBeastDao(): TailBeastDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
    abstract fun clanDao(): ClanDao
    abstract fun akatsukiDao(): AkatsukiDao
    abstract fun karaDao(): KaraDao
}