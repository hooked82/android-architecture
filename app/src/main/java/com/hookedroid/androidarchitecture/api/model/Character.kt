package com.hookedroid.androidarchitecture.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(@PrimaryKey val id: Int,
                     val name: String,
                     val status: String,
                     val species: String,
                     val type: String,
                     val gender: String,
                     val origin: Origin,
                     val lastKnownLocation: Origin,
                     val imageUrl: String,
                     val episodes: List<String>,
                     val url: String,
                     val createdDate: String)