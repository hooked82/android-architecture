package com.hookedroid.androidarchitecture.data

import androidx.paging.PagedList
import androidx.paging.PagingRequestHelper
import com.hookedroid.androidarchitecture.api.CharacterApi
import com.hookedroid.androidarchitecture.api.model.ApiResponse
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.data.repository.CharacterRepository
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
    var reachedEnd = false

    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            characterApi.getCharacters(networkPage).enqueue(createWebserviceCallback(it))
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Character) {
        if (!reachedEnd) {
            helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
                characterApi.getCharacters((itemAtEnd.id / CharacterRepository.DEFAULT_NETWORK_PAGE_SIZE) + 1)
                    .enqueue(createWebserviceCallback(it))
            }
        } else {
            networkState.postValue(NetworkState.ENDED)
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

            override fun onResponse(call: Call<ApiResponse<Character>>, response: Response<ApiResponse<Character>>) {
                //TODO - This logic isn't perfect as the last page may contain the exact amount we requested, but will
                //work for now until a better solution is figured out.
                response.body()?.let {
                    reachedEnd = it.results.size < CharacterRepository.DEFAULT_NETWORK_PAGE_SIZE
                } ?: run {
                    reachedEnd = true
                }

                insertItemsIntoDb(response, it)
            }
        }
    }
}