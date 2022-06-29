package com.arrazyfathan.rickmorty.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.arrazyfathan.rickmorty.data.pagingsource.CharactersPagingSource
import com.arrazyfathan.rickmorty.data.remote.api.ApiHelper
import com.arrazyfathan.rickmorty.data.remote.response.SingleCharacterResponse
import javax.inject.Inject

class RickMortyRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getAllCharacters(): LiveData<PagingData<SingleCharacterResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiHelper)
            },
            initialKey = 1
        ).liveData
    }

    suspend fun getCharacter(characterId: Int) = apiHelper.getCharacterById(characterId)
}
