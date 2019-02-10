package com.hookedroid.androidarchitecture.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePagedListAdapter<
        T,
        L : BasePagedListAdapter.OnItemClickListener<T>,
        VH : BaseViewHolder<T, L>>(context: Context,
                                   diffCallback: DiffUtil.ItemCallback<T>,
                                   private val listener: L? = null)
    : PagedListAdapter<T, VH>(diffCallback) {

    interface OnItemClickListener<T> {
        fun onItemClicked(item: T)
    }

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)

        item?.let {
            holder.onBind(item, listener)
        } ?: run {
            // Null defines a placeholder item.  Row will be invalidated when actual object is loaded from DB
            holder.clear()
        }
    }

    fun inflate(@LayoutRes layout: Int, parent: ViewGroup?, attachToRoot: Boolean = false): View {
        return mInflater.inflate(layout, parent, attachToRoot)
    }

    fun inflateBinding(@LayoutRes layout: Int, parent: ViewGroup?, attachToRoot: Boolean = false): ViewDataBinding {
        return DataBindingUtil.inflate(mInflater, layout, parent, attachToRoot)
    }
}