package com.hookedroid.androidarchitecture.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T, L : BasePagedListAdapter.OnItemClickListener<T>>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: T?, listener: L?)
    abstract fun clear()
}