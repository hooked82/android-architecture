package com.hookedroid.androidarchitecture.location

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hookedroid.androidarchitecture.R
import com.hookedroid.androidarchitecture.adapter.BasePagedListAdapter
import com.hookedroid.androidarchitecture.adapter.NetworkStateViewHolder
import com.hookedroid.androidarchitecture.api.model.Location

class LocationPagedListAdapter(private val itemClickCallback: (Location) -> Unit,
                               private val retryCallback: () -> Unit)
    : BasePagedListAdapter<Location>(Location.diffCallback) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_location -> (holder as LocationViewHolder).onBind(getItem(position))
            R.layout.item_network_state -> (holder as NetworkStateViewHolder).onBind(networkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_location -> LocationViewHolder.create(parent, itemClickCallback)
            R.layout.item_network_state -> NetworkStateViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_location
        }
    }
}