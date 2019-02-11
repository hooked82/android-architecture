package com.hookedroid.androidarchitecture.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Episode(@PrimaryKey val id: Int,
                   val name: String,
                   @SerializedName("air_date") val airDate: String,
                   @SerializedName("episode") val code: String,
                   val characters: List<String>,
                   val url: String,
                   @SerializedName("created") val createdDate: String,
                   val position: Int)