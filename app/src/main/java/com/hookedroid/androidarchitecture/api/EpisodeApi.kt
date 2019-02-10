package com.hookedroid.androidarchitecture.api

import com.hookedroid.androidarchitecture.api.model.ApiResponse
import com.hookedroid.androidarchitecture.api.model.Episode
import retrofit2.http.GET

interface EpisodeApi {

    @GET("episode")
    fun getEpisodes(): ApiResponse<Episode>

    @GET("episode/{id}")
    fun getEpisodeById(id: Int): Episode
}