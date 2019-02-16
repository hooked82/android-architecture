package com.hookedroid.androidarchitecture.api.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Location(@PrimaryKey val id: Int,
                    val name: String,
                    val type: String,
                    val dimension: String,
                    val residents: List<String>,
                    val url: String,
                    @SerializedName("created") val createdDate: String) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Location>() {
            override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem == newItem
            }
        }
    }
}