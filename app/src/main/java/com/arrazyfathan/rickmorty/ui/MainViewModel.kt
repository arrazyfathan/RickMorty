package com.arrazyfathan.rickmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arrazyfathan.rickmorty.data.remote.response.SingleCharacterResponse
import com.arrazyfathan.rickmorty.repository.RickMortyRepository
import com.arrazyfathan.rickmorty.utils.NetworkHelper
import com.arrazyfathan.rickmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val rickMortyRepository: RickMortyRepository
) : ViewModel() {

    private val _character = MutableLiveData<Resource<SingleCharacterResponse>>()
    val character: LiveData<Resource<SingleCharacterResponse>> get() = _character

    init {
        getCharacterById(1)
    }

    fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            _character.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                rickMortyRepository.getCharacter(characterId).let {
                    if (it.isSuccessful) {
                        _character.postValue(Resource.success(it.body()))
                    } else {
                        _character.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                _character.postValue(Resource.error("No Internet connection", null))
            }
        }
    }

    suspend fun getCharactersList(): LiveData<PagingData<SingleCharacterResponse>> {
        return rickMortyRepository.getAllCharacters().cachedIn(viewModelScope)
    }
}
