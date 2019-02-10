package com.hookedroid.androidarchitecture.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Origin(@PrimaryKey val name: String,
                  val url: String)