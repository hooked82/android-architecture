package com.hookedroid.androidarchitecture.data.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hookedroid.androidarchitecture.api.model.Character

@Dao
interface CharacterDao {

    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characters: List<Character>)

    @Query("SELECT * FROM character WHERE id = :id")
    fun getCharacter(id: Int) : LiveData<Character>

    @Query("SELECT * FROM character")
    fun getCharacters(): DataSource.Factory<Int, Character>

    @Query("DELETE FROM character")
    fun deleteAll()
}