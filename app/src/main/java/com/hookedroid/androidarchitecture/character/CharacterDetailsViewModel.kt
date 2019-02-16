package com.hookedroid.androidarchitecture.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hookedroid.androidarchitecture.data.repository.CharacterRepository
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(charactersRepository: CharacterRepository) : ViewModel() {

    private val characterId = MutableLiveData<Int>()

    val character = Transformations.switchMap(characterId) { charactersRepository.getById(it) }
//    val episodes = Transformations.map(characterId) { episodesRepository.getByCharacterId(it) }
//    val locations = Transformations.map(characterId) { locationsRepository.getByCharacterId(it) }

    fun showCharacter(id: Int) {
        characterId.value = id
    }
}