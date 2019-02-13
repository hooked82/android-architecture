package com.hookedroid.androidarchitecture.character

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hookedroid.androidarchitecture.R
import com.hookedroid.androidarchitecture.adapter.BasePagedListAdapter
import com.hookedroid.androidarchitecture.adapter.NetworkStateViewHolder
import com.hookedroid.androidarchitecture.api.model.Character

class CharacterPagedListAdapter(private val itemClickCallback: (Character) -> Unit,
                                private val retryCallback: () -> Unit)
    : BasePagedListAdapter<Character>(Character.diffCallback) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_character -> (holder as CharacterViewHolder).onBind(getItem(position))
            R.layout.item_network_state -> (holder as NetworkStateViewHolder).onBind(networkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_character -> CharacterViewHolder.create(parent, itemClickCallback)
            R.layout.item_network_state -> NetworkStateViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_character
        }
    }
}