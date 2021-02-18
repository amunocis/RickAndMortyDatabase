package com.example.rickmorty.model.remote

import com.example.rickmorty.model.local.entities.Character
import com.example.rickmorty.model.remote.pojo.CharacterWrapper

fun fromInternetToCharacterEntity(wrapper: CharacterWrapper): List<Character> {
    return wrapper.results.map { Character(name = it.name) }
}