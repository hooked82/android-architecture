package com.hookedroid.androidarchitecture.data

import androidx.paging.PagedList
import androidx.paging.PagingRequestHelper
import com.hookedroid.androidarchitecture.api.CharacterApi
import com.hookedroid.androidarchitecture.api.model.ApiResponse
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.util.AppExecutors
import com.hookedroid.androidarchitecture.util.createStatusLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterBoundaryCallback(
    private val characterApi: CharacterApi,
    private val appExecutors: AppExecutors,
    private val handleResponse: (ApiResponse<Character>?) -> Unit,
    private val networkPage: Int) : PagedList.BoundaryCallback<Character>() {

    val helper = PagingRequestHelper(appExecutors.diskIO())
    val networkState = helper.createStatusLiveData()

    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            characterApi.getCharacters(1).enqueue(createWebserviceCallback(it))
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Character) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            characterApi.getCharacters((itemAtEnd.id / 20) + 1).enqueue(createWebserviceCallback(it))
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: Character) {
        // Ignored as we only ever append to items in the DB
    }

    private fun insertItemsIntoDb(response: Response<ApiResponse<Character>>, it: PagingRequestHelper.Request.Callback) {
        appExecutors.diskIO().execute {
            handleResponse(response.body())
            it.recordSuccess()
        }
    }

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback): Callback<ApiResponse<Character>> {
        return object : Callback<ApiResponse<Character>> {
            override fun onFailure(call: Call<ApiResponse<Character>>, t: Throwable) {
                it.recordFailure(t)
            }

            override fun onResponse(
                call: Call<ApiResponse<Character>>,
                response: Response<ApiResponse<Character>>) {
                insertItemsIntoDb(response, it)
            }
        }
    }
}