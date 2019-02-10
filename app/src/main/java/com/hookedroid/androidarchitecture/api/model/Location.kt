package com.hookedroid.androidarchitecture.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(@PrimaryKey val id: Int,
                    val name: String,
                    val type: String,
                    val dimension: String,
                    val residents: List<String>,
                    val url: String,
                    val createdDate: String)