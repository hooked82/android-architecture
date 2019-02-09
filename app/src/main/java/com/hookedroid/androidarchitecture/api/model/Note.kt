package com.hookedroid.androidarchitecture.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(@PrimaryKey(autoGenerate = true) val id: Int)