package com.example.rickmorty.model.remote.pojo

import com.example.rickmorty.model.local.entities.Character
import com.google.gson.annotations.SerializedName

data class CharacterWrapper(
    @SerializedName("results")
    val results: List<Character>

)
