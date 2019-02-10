package com.hookedroid.androidarchitecture.character

import androidx.lifecycle.ViewModel
import com.hookedroid.androidarchitecture.data.repository.CharacterRepository
import javax.inject.Inject

class CharactersViewModel @Inject constructor(characterRepository: CharacterRepository) : ViewModel() {
}