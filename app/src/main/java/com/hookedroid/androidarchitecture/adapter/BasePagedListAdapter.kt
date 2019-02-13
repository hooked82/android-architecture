package com.hookedroid.androidarchitecture.adapter

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hookedroid.androidarchitecture.data.NetworkState

abstract class BasePagedListAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>)
    : PagedListAdapter<T, RecyclerView.ViewHolder>(diffCallback) {

    private var _networkState: NetworkState? = null
    val networkState
        get() = _networkState

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun hasExtraRow() = _networkState != null && _networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = _networkState
        val hadExtraRow = hasExtraRow()
        _networkState = newNetworkState
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
}