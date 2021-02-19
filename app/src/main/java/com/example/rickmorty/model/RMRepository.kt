package com.example.rickmorty.model

import android.util.Log
import com.example.rickmorty.model.local.dao.CharacterDao
import com.example.rickmorty.model.local.dao.PlacesDao
import com.example.rickmorty.model.local.entities.Character
import com.example.rickmorty.model.remote.RetrofitInstance
import com.example.rickmorty.model.remote.fromInternetToCharacterEntity
import com.example.rickmorty.model.remote.fromInternetToPlacesEntity

class RMRepository(private val characterDao: CharacterDao, private val placesDao: PlacesDao) {
    private val networkService = RetrofitInstance.retrofitInstance()
    val characterListLiveData = characterDao.getAllCharacterList()
    val placesListLiveData = placesDao.getAllPlacesList()

    suspend fun fetchCharacter() {
        val service = kotlin.runCatching { networkService.fetchCharacterList() }
        service.onSuccess {
            when(it.code()) {
                200 -> it.body()?.let {
                    characterDao.insertAllCharacterList(fromInternetToCharacterEntity(it))
                }
                else -> Log.d("REPO", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }

    suspend fun fetchPlaces() {
        val service = kotlin.runCatching { networkService.fetchPlacesList() }
        service.onSuccess {
            when(it.code()) {
                200 -> it.body()?.let {
                    placesDao.insertAllPlacesList(fromInternetToPlacesEntity(it))
                }
                else -> Log.d("REPO", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }

    suspend fun updateCharFavImages(character: Character) {
        characterDao.updateCharFavImage(character)
    }

    suspend fun deleteAllCharacterFavorites() {
        characterDao.deleteAllCharacterFavorites()
    }

    suspend fun getAllCharacterFavorites(character: Character) {
        characterDao.getAllCharacterFavorites()
    }
}