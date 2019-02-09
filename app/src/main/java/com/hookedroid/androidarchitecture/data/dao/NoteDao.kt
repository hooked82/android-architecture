package com.hookedroid.androidarchitecture.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hookedroid.androidarchitecture.api.model.Note

@Dao
interface NoteDao {

    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(vararg notes: Note)
}