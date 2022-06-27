package com.arrazyfathan.rickmorty.data.remote.api

import com.arrazyfathan.rickmorty.data.remote.response.SingleCharacterResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getAllCharacters()
    suspend fun getCharacterById(characterId: Int): Response<SingleCharacterResponse>
}
