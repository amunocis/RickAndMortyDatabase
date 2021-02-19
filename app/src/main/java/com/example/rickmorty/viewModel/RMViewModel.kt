package com.example.rickmorty.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.model.RMRepository
import com.example.rickmorty.model.local.RMDatabase
import com.example.rickmorty.model.local.entities.Character
import com.example.rickmorty.model.local.entities.Places
import kotlinx.coroutines.launch

class RMViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RMRepository
    var selCat = 2

    init {
        val db = RMDatabase.getDatabase(application)

        val characterDao = db.characterDao()
        val placesDao = db.placesDao()

        repository = RMRepository(characterDao, placesDao)


        viewModelScope.launch {
            repository.fetchCharacter()
        }

        viewModelScope.launch {
            repository.fetchPlaces()
        }
    }

    fun getCharacterList(): LiveData<List<Character>> = repository.characterListLiveData
    fun getPlacesList(): LiveData<List<Places>> = repository.placesListLiveData
}