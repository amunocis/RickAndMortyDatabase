package com.example.rickmorty.model.remote

import com.example.rickmorty.model.local.entities.Character
import com.example.rickmorty.model.local.entities.Places
import com.example.rickmorty.model.remote.pojo.CharacterWrapper
import com.example.rickmorty.model.remote.pojo.PlacesWrapper

fun fromInternetToCharacterEntity(wrapper: CharacterWrapper): List<Character> {
    return wrapper.results.map {
        Character(
            name = it.name,
            image = it.image,
            species = it.species)
    }
}

fun fromInternetToPlacesEntity(wrapper: PlacesWrapper): List<Places> {
    return wrapper.results.map {
        Places(it.name)
    }
}