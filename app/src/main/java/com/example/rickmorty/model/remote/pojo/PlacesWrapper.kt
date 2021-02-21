package com.example.rickmorty.model.remote.pojo

import com.example.rickmorty.model.local.entities.Places
import com.google.gson.annotations.SerializedName

data class PlacesWrapper(
    @SerializedName("results")
    val results: List<Places>,
)
