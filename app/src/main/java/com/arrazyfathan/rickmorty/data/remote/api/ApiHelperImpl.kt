package com.arrazyfathan.rickmorty.data.remote.api

import com.arrazyfathan.rickmorty.data.remote.response.CharactersPageResponse
import com.arrazyfathan.rickmorty.data.remote.response.SingleCharacterResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val rickMortyAPI: RickMortyAPI) : ApiHelper {

    override suspend fun getAllCharacters(page: Int): Response<CharactersPageResponse> {
        return rickMortyAPI.getAllCharacters(page)
    }

    override suspend fun getCharacterById(characterId: Int): Response<SingleCharacterResponse> {
        return rickMortyAPI.getCharacter(characterId)
    }
}
