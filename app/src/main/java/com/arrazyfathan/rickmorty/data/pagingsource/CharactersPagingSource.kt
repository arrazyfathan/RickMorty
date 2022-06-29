package com.arrazyfathan.rickmorty.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arrazyfathan.rickmorty.data.remote.api.ApiHelper
import com.arrazyfathan.rickmorty.data.remote.response.SingleCharacterResponse
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(private val apiHelper: ApiHelper) :
    PagingSource<Int, SingleCharacterResponse>() {

    override fun getRefreshKey(state: PagingState<Int, SingleCharacterResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SingleCharacterResponse> {
        return try {
            val position = params.key ?: 1
            val response = apiHelper.getAllCharacters(position)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
