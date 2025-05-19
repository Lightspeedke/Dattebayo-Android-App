package com.jeric.narutobook.data.remote

import com.jeric.narutobook.data.remote.dto.akatsuki.AkatsukiResponse
import com.jeric.narutobook.data.remote.dto.buruto.BorutoResponse
import com.jeric.narutobook.data.remote.dto.character.CharacterDto
import com.jeric.narutobook.data.remote.dto.character.CharactersResponse
import com.jeric.narutobook.data.remote.dto.clan.ClanResponse
import com.jeric.narutobook.data.remote.dto.tailbeast.TailBeastResponse
import com.jeric.narutobook.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NarutoApi {

    @GET("characters")
    suspend fun getAllCharacters(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = Constants.SIZE
    ): CharactersResponse

    @GET("characters")
    suspend fun searchCharactersByName(
        @Query("name") name: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20
    ): CharactersResponse

    @GET("characters/{id}")
    suspend fun getCharacterById(
        @Path("id") id: String
    ): CharacterDto

    @GET("tailed-beasts")
    suspend fun getAllTailBeast(): TailBeastResponse

    @GET("clans")
    suspend fun getClan(): ClanResponse

    @GET("akatsuki")
    suspend fun getAkatsuki(): AkatsukiResponse

    @GET("kara")
    suspend fun getBoruto(): BorutoResponse

}