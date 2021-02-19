package com.example.rickmorty.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickmorty.model.local.entities.Places

@Dao
interface PlacesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlacesList(listPlaces: List<Places>)

    @Query("SELECT * FROM places_table ORDER BY name ASC")
    fun getAllPlacesList(): LiveData<List<Places>>
}