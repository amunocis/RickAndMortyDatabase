package com.example.rickmorty.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class Character(
    @PrimaryKey val name: String)
