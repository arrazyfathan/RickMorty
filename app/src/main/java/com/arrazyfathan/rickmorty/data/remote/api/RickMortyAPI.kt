package com.arrazyfathan.rickmorty.data.remote.api

import com.arrazyfathan.rickmorty.data.remote.response.CharactersPageResponse
import com.arrazyfathan.rickmorty.data.remote.response.SingleCharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyAPI {

    @GET("api/character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<CharactersPageResponse>

    @GET("api/character/{character_id}")
    suspend fun getCharacter(
        @Path("character_id") characterId: Int
    ): Response<SingleCharacterResponse>
}
