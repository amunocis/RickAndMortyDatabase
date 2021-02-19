package com.example.rickmorty.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickmorty.model.local.dao.CharacterDao
import com.example.rickmorty.model.local.dao.PlacesDao
import com.example.rickmorty.model.local.entities.Character
import com.example.rickmorty.model.local.entities.Places

@Database(entities = [Character::class, Places::class], version = 5, exportSchema = false)
abstract class RMDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun placesDao(): PlacesDao

    companion object {
        @Volatile
        private var INSTANCE: RMDatabase? = null

        fun getDatabase(context: Context): RMDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RMDatabase::class.java,
                    "database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}