package com.example.rickmorty.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickmorty.model.local.entities.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacterList(listCharacter: List<Character>)

    @Query("SELECT * FROM character_table ORDER BY name ASC")
    fun getAllCharacterList(): LiveData<List<Character>>
}