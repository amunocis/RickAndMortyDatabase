package com.example.rickmorty.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places_table")
data class Places(
    @PrimaryKey val name: String)
