package com.arrazyfathan.rickmorty.repository

import com.arrazyfathan.rickmorty.data.remote.api.ApiHelper
import javax.inject.Inject

class RickMortyRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getAllCharacters() = apiHelper.getAllCharacters()
    suspend fun getCharacter(characterId: Int) = apiHelper.getCharacterById(characterId)
}
