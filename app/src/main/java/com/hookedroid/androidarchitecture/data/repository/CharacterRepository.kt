package com.hookedroid.androidarchitecture.data.repository

import com.hookedroid.androidarchitecture.data.dao.CharacterDao
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val characterDao: CharacterDao) {


}