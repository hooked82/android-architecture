package com.hookedroid.androidarchitecture.api

import com.hookedroid.androidarchitecture.api.model.ApiResponse
import com.hookedroid.androidarchitecture.api.model.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("api/character/")
    fun getCharacters(@Query("page") page: Int): Call<ApiResponse<Character>>

    @GET("api/character/{id}")
    fun getCharacterById(id: Int): Call<Character>
}