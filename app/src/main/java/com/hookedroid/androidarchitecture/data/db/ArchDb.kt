package com.hookedroid.androidarchitecture.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.api.model.Episode
import com.hookedroid.androidarchitecture.api.model.Location
import com.hookedroid.androidarchitecture.data.converters.RoomConverters
import com.hookedroid.androidarchitecture.data.dao.CharacterDao
import com.hookedroid.androidarchitecture.data.dao.LocationDao

@Database(
    entities = [ Character::class, Location::class, Episode::class ],
    version = 5,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class ArchDb : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
}