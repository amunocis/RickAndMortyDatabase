package com.example.rickmorty.model

import android.util.Log
import android.widget.Toast
import com.example.rickmorty.model.local.dao.CharacterDao
import com.example.rickmorty.model.remote.RetrofitInstance
import com.example.rickmorty.model.remote.fromInternetToCharacterEntity

class RMRepository(private val characterDao: CharacterDao) {
    private val networkService = RetrofitInstance.retrofitInstance()
    val characterListLiveData = characterDao.getAllCharacterList()

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


}