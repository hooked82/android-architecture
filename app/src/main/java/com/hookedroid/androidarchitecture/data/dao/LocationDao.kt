package com.hookedroid.androidarchitecture.data.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hookedroid.androidarchitecture.api.model.Location

@Dao
interface LocationDao {

    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(locations: List<Location>)

    @Query("SELECT * FROM location WHERE id = :id")
    fun getLocation(id: Int) : LiveData<Location>

    @Query("SELECT * FROM location")
    fun getLocations(): DataSource.Factory<Int, Location>

    @Query("DELETE FROM location")
    fun deleteAll()
}