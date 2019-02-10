package com.hookedroid.androidarchitecture.api.model

import androidx.recyclerview.widget.DiffUtil
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
                     val createdDate: String,
                     val position: Int) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
}