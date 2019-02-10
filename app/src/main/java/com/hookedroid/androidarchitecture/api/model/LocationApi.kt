package com.hookedroid.androidarchitecture.api.model

import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApi {

    @GET("location")
    fun getLocations(): ApiResponse<Location>

    @GET("location/{id}")
    fun getLocationById(@Path("id") id: Int): Location
}