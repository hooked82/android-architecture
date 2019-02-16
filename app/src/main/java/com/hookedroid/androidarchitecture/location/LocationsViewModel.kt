package com.hookedroid.androidarchitecture.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hookedroid.androidarchitecture.data.repository.LocationRepository
import javax.inject.Inject

class LocationsViewModel @Inject constructor(locationRepository: LocationRepository) : ViewModel() {

    private val page = MutableLiveData<Int>()
    private val repoResult = Transformations.map(page) { locationRepository.getByPage(it) }

    val locations = Transformations.switchMap(repoResult) { it.pagedList }!!
    val networkState = Transformations.switchMap(repoResult) { it.networkState }!!
    val refreshState = Transformations.switchMap(repoResult) { it.refreshState }!!

    fun showLocations(pageNumber: Int) {
        page.value = pageNumber
    }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun retry() {
        repoResult.value?.retry?.invoke()
    }
}