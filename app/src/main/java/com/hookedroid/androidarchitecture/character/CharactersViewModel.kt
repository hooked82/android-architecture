package com.hookedroid.androidarchitecture.character

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hookedroid.androidarchitecture.data.repository.CharacterRepository
import javax.inject.Inject

class CharactersViewModel @Inject constructor(characterRepository: CharacterRepository) : ViewModel() {

    private val page = MutableLiveData<Int>()
    val reachedEnd = MutableLiveData<Boolean>()
    private val repoResult = Transformations.map(page) {
        characterRepository.getByPage(it, this::handleReachedEndResponse)
    }

    private fun handleReachedEndResponse() {
        Log.d("CharactersViewModel", "View Model Has Reached The End")
        reachedEnd.value = true
    }

    val characters = Transformations.switchMap(repoResult) { it.pagedList }
    val networkState = Transformations.switchMap(repoResult) { it.networkState }
    val refreshState = Transformations.switchMap(repoResult) { it.refreshState }

    fun showCharacters(pageNumber: Int) {
        page.value = pageNumber
    }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun retry() {
        repoResult.value?.retry?.invoke()
    }
}