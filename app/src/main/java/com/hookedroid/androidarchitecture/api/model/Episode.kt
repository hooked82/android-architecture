package com.hookedroid.androidarchitecture.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Episode(@PrimaryKey val id: Int,
                   val name: String,
                   val airDate: String,
                   val code: String,
                   val characters: List<String>,
                   val url: String,
                   val createdDate: String)