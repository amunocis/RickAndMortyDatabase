package com.example.rickmorty.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rickmorty.model.local.entities.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacterList(listCharacter: List<Character>)

    @Query("SELECT * FROM character_table ORDER BY name ASC")
    fun getAllCharacterList(): LiveData<List<Character>>

    @Update
    suspend fun updateCharFavImage(character: Character)


    // parametro listas**

    //Query method parameters should either be a type that can be converted into a database column
    // or a List / Array that contains such type. You can consider adding a Type Adapter for this.
    @Query("UPDATE character_table SET fav = 0 WHERE fav = 1")
    suspend fun deleteAllCharacterFavorites()

    @Query("SELECT * FROM character_table WHERE fav = 1")
    fun getAllCharacterFavorites(): LiveData<List<Character>>

}