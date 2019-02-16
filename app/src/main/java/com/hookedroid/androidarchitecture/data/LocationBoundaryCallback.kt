package com.hookedroid.androidarchitecture.data

import androidx.paging.PagedList
import androidx.paging.PagingRequestHelper
import com.hookedroid.androidarchitecture.api.LocationApi
import com.hookedroid.androidarchitecture.api.model.ApiResponse
import com.hookedroid.androidarchitecture.api.model.Location
import com.hookedroid.androidarchitecture.data.repository.LocationRepository
import com.hookedroid.androidarchitecture.util.AppExecutors
import com.hookedroid.androidarchitecture.util.createStatusLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationBoundaryCallback(
    private val locationApi: LocationApi,
    private val appExecutors: AppExecutors,
    private val handleResponse: (ApiResponse<Location>?) -> Unit,
    private val networkPage: Int) : PagedList.BoundaryCallback<Location>() {

    val helper = PagingRequestHelper(appExecutors.diskIO())
    val networkState = helper.createStatusLiveData()
    var reachedEnd = false

    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            locationApi.getLocations(networkPage).enqueue(createWebserviceCallback(it))
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Location) {
        if (!reachedEnd) {
            helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
                locationApi.getLocations((itemAtEnd.id / LocationRepository.DEFAULT_NETWORK_PAGE_SIZE) + 1)
                    .enqueue(createWebserviceCallback(it))
            }
        } else {
            networkState.postValue(NetworkState.ENDED)
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: Location) {
        // Ignored as we only ever append to items in the DB
    }

    private fun insertItemsIntoDb(response: Response<ApiResponse<Location>>, it: PagingRequestHelper.Request.Callback) {
        appExecutors.diskIO().execute {
            handleResponse(response.body())
            it.recordSuccess()
        }
    }

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback): Callback<ApiResponse<Location>> {
        return object : Callback<ApiResponse<Location>> {
            override fun onFailure(call: Call<ApiResponse<Location>>, t: Throwable) {
                it.recordFailure(t)
            }

            override fun onResponse(call: Call<ApiResponse<Location>>, response: Response<ApiResponse<Location>>) {
                //TODO - This logic isn't perfect as the last page may contain the exact amount we requested, but will
                //work for now until a better solution is figured out.
                response.body()?.let {
                    reachedEnd = it.results.size < LocationRepository.DEFAULT_NETWORK_PAGE_SIZE
                } ?: run {
                    reachedEnd = true
                }

                insertItemsIntoDb(response, it)
            }
        }
    }
}