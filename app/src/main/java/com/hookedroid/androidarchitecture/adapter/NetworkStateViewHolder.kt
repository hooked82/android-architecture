package com.hookedroid.androidarchitecture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hookedroid.androidarchitecture.data.NetworkState
import com.hookedroid.androidarchitecture.data.Status
import com.hookedroid.androidarchitecture.databinding.ItemNetworkStateBinding

class NetworkStateViewHolder(private val mBinding: ItemNetworkStateBinding, private val retryCallback: () -> Unit)
    : BaseViewHolder<NetworkState, BasePagedListAdapter.OnItemClickListener<NetworkState>>(mBinding.root) {

    init {
        mBinding.networkStateErrorButton.setOnClickListener { retryCallback() }
    }

    override fun onBind(item: NetworkState?, listener: BasePagedListAdapter.OnItemClickListener<NetworkState>?) {
        mBinding.networkStateProgress.visibility = toVisibility(item?.status == Status.RUNNING)
        mBinding.networkStateEndGroup.visibility = toVisibility(item?.status == Status.ENDED)
        mBinding.networkStateErrorGroup.visibility = toVisibility(item?.status == Status.FAILED)
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateViewHolder {
            return NetworkStateViewHolder(ItemNetworkStateBinding.inflate(LayoutInflater.from(parent.context), parent, false), retryCallback)
        }

        fun toVisibility(constraint : Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}