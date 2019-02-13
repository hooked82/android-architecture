package com.hookedroid.androidarchitecture.character

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hookedroid.androidarchitecture.R
import com.hookedroid.androidarchitecture.adapter.NetworkStateViewHolder
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.data.NetworkState

class CharacterPagedListAdapter(private val retryCallback: () -> Unit) : PagedListAdapter<Character, RecyclerView.ViewHolder>(Character.diffCallback) {

    private var networkState: NetworkState? = null
    private var hasReachedEnd: Boolean = false

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_character -> (holder as CharacterViewHolder).onBind(getItem(position), null)
            R.layout.item_network_state -> (holder as NetworkStateViewHolder).onBind(networkState, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_character -> CharacterViewHolder.create(parent)
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

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    fun hasReachedEnd() {
        hasReachedEnd = true
    }
}