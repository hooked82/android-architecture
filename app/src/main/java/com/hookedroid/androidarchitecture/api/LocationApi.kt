package com.hookedroid.androidarchitecture.api

import com.hookedroid.androidarchitecture.api.model.ApiResponse
import com.hookedroid.androidarchitecture.api.model.Location
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

    @GET("api/location/")
    fun getLocations(@Query("page") page: Int): Call<ApiResponse<Location>>

    @GET("api/location/{id}")
    fun getLocationById(id: Int): Call<Location>
}