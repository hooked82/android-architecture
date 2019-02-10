package com.hookedroid.androidarchitecture.api

import com.hookedroid.androidarchitecture.api.model.Character
import retrofit2.http.GET

interface CharacterApi {

    @GET("character")
    fun getCharacters(): List<Character>

    @GET("character/{id}")
    fun getCharacterById(id: Int): Character
}