package com.jeric.narutobook.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jeric.narutobook.data.local.entity.AkatsukiFamilyEntity
import com.jeric.narutobook.data.local.entity.AkatsukiPersonalEntity
import com.jeric.narutobook.data.local.entity.FamilyEntity
import com.jeric.narutobook.data.local.entity.KaraFamilyEntity
import com.jeric.narutobook.data.local.entity.KaraPersonalEntity
import com.jeric.narutobook.data.local.entity.PersonalEntity

class NarutoTypeConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromList(list: List<String>): String {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromFamilyEntity(status: FamilyEntity?): String {
        return gson.toJson(status)
    }

    @TypeConverter
    fun toFamilyEntity(value: String): FamilyEntity? {
        return gson.fromJson(value, FamilyEntity::class.java)
    }

    @TypeConverter
    fun fromPersonalEntity(status: PersonalEntity?): String {
        return gson.toJson(status)
    }

    @TypeConverter
    fun toPersonalEntity(value: String): PersonalEntity? {
        return gson.fromJson(value, PersonalEntity::class.java)
    }

    @TypeConverter
    fun fromCharacterList(list: List<Int>): String {
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toCharacterList(value: String): List<Int> {
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromFamilyAkatsukiEntity(status: AkatsukiFamilyEntity?): String {
        return gson.toJson(status)
    }

    @TypeConverter
    fun toFamilyAkatsukiEntity(value: String): AkatsukiFamilyEntity? {
        return gson.fromJson(value, AkatsukiFamilyEntity::class.java)
    }

    @TypeConverter
    fun fromPersonalAkatsukiEntity(status: AkatsukiPersonalEntity?): String {
        return gson.toJson(status)
    }

    @TypeConverter
    fun toPersonalAkatsukiEntity(value: String): AkatsukiPersonalEntity? {
        return gson.fromJson(value, AkatsukiPersonalEntity::class.java)
    }

    //
    @TypeConverter
    fun fromFamilyKaraEntity(status: KaraFamilyEntity?): String {
        return gson.toJson(status)
    }

    @TypeConverter
    fun toFamilyKaraEntity(value: String): KaraFamilyEntity? {
        return gson.fromJson(value, KaraFamilyEntity::class.java)
    }

    @TypeConverter
    fun fromPersonalKaraEntity(status: KaraPersonalEntity?): String {
        return gson.toJson(status)
    }

    @TypeConverter
    fun toPersonalKaraEntity(value: String): KaraPersonalEntity? {
        return gson.fromJson(value, KaraPersonalEntity::class.java)
    }

}