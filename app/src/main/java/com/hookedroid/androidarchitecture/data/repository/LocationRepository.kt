package com.hookedroid.androidarchitecture.data.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.hookedroid.androidarchitecture.api.LocationApi
import com.hookedroid.androidarchitecture.api.model.ApiResponse
import com.hookedroid.androidarchitecture.api.model.Location
import com.hookedroid.androidarchitecture.data.Listing
import com.hookedroid.androidarchitecture.data.LocationBoundaryCallback
import com.hookedroid.androidarchitecture.data.NetworkState
import com.hookedroid.androidarchitecture.data.db.ArchDb
import com.hookedroid.androidarchitecture.util.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(private val db: ArchDb,
                                             private val locationApi: LocationApi,
                                             private val appExecutors: AppExecutors) : Repository<Location> {

    @MainThread
    override fun getById(id: Int) : LiveData<Location> {
        return db.locationDao().getLocation(id)
    }

    @MainThread
    override fun getByPage(page: Int): Listing<Location> {
        val boundaryCallback = LocationBoundaryCallback(
            locationApi,
            appExecutors,
            this::insertResultsIntoDb,
            page
        )

        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) {
            refresh()
        }

        val livePagedList = db.locationDao().getLocations().toLiveData(
            pageSize = DEFAULT_NETWORK_PAGE_SIZE,
            boundaryCallback = boundaryCallback
        )

        return Listing(
            pagedList = livePagedList,
            networkState =  boundaryCallback.networkState,
            retry = {
                boundaryCallback.helper.retryAllFailed()
            },
            refresh = {
                boundaryCallback.networkState.postValue(NetworkState.LOADING)
                refreshTrigger.value = null
            },
            refreshState = refreshState
        )
    }

    @MainThread
    private fun refresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()
        networkState.value = NetworkState.LOADING
        locationApi.getLocations(1).enqueue(object : Callback<ApiResponse<Location>> {
            override fun onFailure(call: Call<ApiResponse<Location>>, t: Throwable) {
                networkState.value = NetworkState.error(t.message)
            }

            override fun onResponse(call: Call<ApiResponse<Location>>, response: Response<ApiResponse<Location>>) {
                appExecutors.diskIO().execute {
                    db.runInTransaction {
                        db.locationDao().deleteAll()
                        insertResultsIntoDb(response.body())
                    }

                    networkState.postValue(NetworkState.LOADED)
                }
            }
        })

        return networkState
    }

    private fun insertResultsIntoDb(body: ApiResponse<Location>?) {
        body?.results?.let {
            db.runInTransaction {
                db.locationDao().insert(it)
            }
        }
    }

    companion object {
        const val DEFAULT_NETWORK_PAGE_SIZE = 20
    }
}