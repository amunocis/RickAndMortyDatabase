package com.example.rickmorty.model.remote

import com.example.rickmorty.model.remote.pojo.CharacterWrapper
import com.example.rickmorty.model.remote.pojo.PlacesWrapper
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("character")
    suspend fun fetchCharacterList(): Response<CharacterWrapper>

    @GET("location")
    suspend fun fetchPlacesList(): Response<PlacesWrapper>
}