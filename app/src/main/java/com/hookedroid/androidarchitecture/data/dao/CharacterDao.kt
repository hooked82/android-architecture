package com.hookedroid.androidarchitecture.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hookedroid.androidarchitecture.api.model.Character

@Dao
interface CharacterDao {

    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(vararg characters: Character)
}