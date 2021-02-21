package com.example.rickmorty.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rickmorty.model.local.entities.Places

@Dao
interface PlacesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlacesList(listPlaces: List<Places>)

    @Query("SELECT * FROM places_table ORDER BY name ASC")
    fun getAllPlacesList(): LiveData<List<Places>>

    @Update
    suspend fun updatePlaceFavImage(places: Places)

    @Query("UPDATE places_table SET fav = 0 WHERE fav = 1")
    suspend fun deleteAllPlaceFavorites()

    @Query("SELECT * FROM places_table WHERE fav = 1")
    fun showAllPlaceFavorites(): LiveData<List<Places>>
}