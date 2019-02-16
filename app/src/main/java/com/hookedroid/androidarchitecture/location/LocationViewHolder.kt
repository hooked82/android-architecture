package com.hookedroid.androidarchitecture.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hookedroid.androidarchitecture.api.model.Location
import com.hookedroid.androidarchitecture.databinding.ItemLocationBinding

class LocationViewHolder(private val mBinding: ItemLocationBinding,
                         private val clickCallback: (Location) -> Unit)
    : RecyclerView.ViewHolder(mBinding.root) {

    fun onBind(item: Location?) {
        mBinding.viewModel = item

        item?.let {
            itemView.setOnClickListener { clickCallback(item) }
        }
    }

    fun clear() {
        mBinding.locationName.text = ""
        mBinding.locationType.text = ""
        mBinding.locationDimension.text = ""
    }

    companion object {
        fun create(parent: ViewGroup, clickCallback: (Location) -> Unit): LocationViewHolder {
            return LocationViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false), clickCallback)
        }
    }
}