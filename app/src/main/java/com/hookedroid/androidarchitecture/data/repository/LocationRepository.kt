package com.hookedroid.androidarchitecture.data.repository

import com.hookedroid.androidarchitecture.api.CharacterApi
import com.hookedroid.androidarchitecture.data.db.ArchDb
import com.hookedroid.androidarchitecture.util.AppExecutors
import javax.inject.Inject

class LocationRepository @Inject constructor(private val db: ArchDb,
                                             private val characterApi: CharacterApi,
                                             private val appExecutors: AppExecutors) {

//    fun getCharacter(id: Int) : LiveData<Character> {
//        return MutableLiveData<Character>()
////        return characterDao.getCharacter(id)
//    }
//
//    @MainThread
//    fun getCharacters(page: Int): Listing<Character> {
//        val boundaryCallback = CharacterBoundaryCallback(
//            characterApi,
//            appExecutors,
//            this::insertResultsIntoDb,
//            page
//        )
//
//        val refreshTrigger = MutableLiveData<Unit>()
//        val refreshState = Transformations.switchMap(refreshTrigger) {
//            refresh()
//        }
//
//        val livePagedList = db.characterDao().getCharacters().toLiveData(
//            pageSize = DEFAULT_NETWORK_PAGE_SIZE,
//            boundaryCallback = boundaryCallback
//        )
//
//        return Listing(
//            pagedList = livePagedList,
//            networkState =  boundaryCallback.networkState,
//            retry = {
//                boundaryCallback.helper.retryAllFailed()
//            },
//            refresh = {
//                refreshTrigger.value = null
//            },
//            refreshState = refreshState
//        )
//    }
//
//    @MainThread
//    private fun refresh(): LiveData<NetworkState> {
//        val networkState = MutableLiveData<NetworkState>()
//        networkState.value = NetworkState.LOADING
//        characterApi.getCharacters(1).enqueue(object : Callback<ApiResponse<Character>> {
//            override fun onFailure(call: Call<ApiResponse<Character>>, t: Throwable) {
//                networkState.value = NetworkState.error(t.message)
//            }
//
//            override fun onResponse(call: Call<ApiResponse<Character>>, response: Response<ApiResponse<Character>>) {
//                appExecutors.diskIO().execute {
//                    db.runInTransaction {
//                        db.characterDao().deleteAll()
//                        insertResultsIntoDb(response.body())
//                    }
//
//                    networkState.postValue(NetworkState.LOADED)
//                }
//            }
//        })
//
//        return networkState
//    }
//
//    private fun insertResultsIntoDb(body: ApiResponse<Character>?) {
//        body?.results?.let {
//            db.runInTransaction {
//                db.characterDao().insert(it)
//            }
//        }
//    }
//
//    companion object {
//        private const val DEFAULT_NETWORK_PAGE_SIZE = 4
//    }
}