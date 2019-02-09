package com.hookedroid.androidarchitecture.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hookedroid.androidarchitecture.api.model.Note
import com.hookedroid.androidarchitecture.data.dao.NoteDao

@Database(
    entities = [ Note::class ],
    version = 1,
    exportSchema = false
)
abstract class ArchDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}