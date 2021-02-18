package com.example.rickmorty.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.model.RMRepository
import com.example.rickmorty.model.local.RMDatabase
import com.example.rickmorty.model.local.entities.Character
import kotlinx.coroutines.launch

class RMViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RMRepository

    init {
        val db = RMDatabase.getDatabase(application)
        val characterDao = db.characterDao()
        repository = RMRepository(characterDao)

        viewModelScope.launch {
            repository.fetchCharacter()
        }
    }

    fun getCharacterList(): LiveData<List<Character>> = repository.characterListLiveData
}