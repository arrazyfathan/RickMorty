package com.arrazyfathan.rickmorty.data.remote.api

import com.arrazyfathan.rickmorty.data.remote.response.SingleCharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyAPI {

    @GET("api/character")
    suspend fun getAllCharacters()

    @GET("api/character/{character_id}")
    suspend fun getCharacter(
        @Path("character_id") characterId: Int
    ): Response<SingleCharacterResponse>
}
