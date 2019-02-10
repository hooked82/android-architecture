package com.hookedroid.androidarchitecture.data.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.hookedroid.androidarchitecture.api.CharacterApi
import com.hookedroid.androidarchitecture.api.model.ApiResponse
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.data.BoundaryCallback
import com.hookedroid.androidarchitecture.data.Listing
import com.hookedroid.androidarchitecture.data.NetworkState
import com.hookedroid.androidarchitecture.data.db.ArchDb
import com.hookedroid.androidarchitecture.util.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val db: ArchDb,
                                              private val characterApi: CharacterApi,
                                              private val appExecutors: AppExecutors) {

    fun getCharacter(id: Int) : LiveData<Character> {
        return MutableLiveData<Character>()
//        return characterDao.getCharacter(id)
    }

    @MainThread
    fun getCharacters(page: Int): Listing<Character> {
        val boundaryCallback = BoundaryCallback(
            characterApi,
            appExecutors,
            this::insertResultsIntoDb,
            page
        )

        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) {
            refresh()
        }

        val livePagedList = db.characterDao().getCharacters().toLiveData(
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
                refreshTrigger.value = null
            },
            refreshState = refreshState
        )
    }

    @MainThread
    private fun refresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()
        networkState.value = NetworkState.LOADING
        characterApi.getCharacters(1).enqueue(object : Callback<ApiResponse<Character>> {
            override fun onFailure(call: Call<ApiResponse<Character>>, t: Throwable) {
                networkState.value = NetworkState.error(t.message)
            }

            override fun onResponse(call: Call<ApiResponse<Character>>, response: Response<ApiResponse<Character>>) {
                appExecutors.diskIO().execute {
                    db.runInTransaction {
                        db.characterDao().deleteAll()
                        insertResultsIntoDb(response.body())
                    }

                    networkState.postValue(NetworkState.LOADED)
                }
            }
        })

        return networkState
    }

    private fun insertResultsIntoDb(body: ApiResponse<Character>?) {
        body?.results?.let {
            db.runInTransaction {
                db.characterDao().insert(it)
            }
        }
    }

    companion object {
        private const val DEFAULT_NETWORK_PAGE_SIZE = 20
    }
}