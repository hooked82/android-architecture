package com.hookedroid.androidarchitecture.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hookedroid.androidarchitecture.BaseFragment
import com.hookedroid.androidarchitecture.api.model.Location
import com.hookedroid.androidarchitecture.data.NetworkState
import com.hookedroid.androidarchitecture.databinding.FragmentLocationsBinding
import com.hookedroid.androidarchitecture.util.sendShortToast

class LocationsFragment : BaseFragment<LocationsViewModel>() {

    private lateinit var mBinding: FragmentLocationsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentLocationsBinding.inflate(inflater, container, false).apply {
            mBinding = this
            setLifecycleOwner(this@LocationsFragment)
            return root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initSwipeToRefresh()

        val adapter = LocationPagedListAdapter(
            { onItemClicked(it) },
            { mViewModel.retry() }
        )

        mBinding.locationsList.layoutManager = LinearLayoutManager(requireContext())
        mBinding.locationsList.adapter = adapter

        mViewModel.locations.observe(this, Observer {
            adapter.submitList(it)
        })
        mViewModel.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })
        mViewModel.showLocations(1)
    }

    private fun initSwipeToRefresh() {
        mViewModel.refreshState.observe(this, Observer {
            mBinding.refreshLayout.isRefreshing = it == NetworkState.LOADING
        })

        mBinding.refreshLayout.setOnRefreshListener {
            mViewModel.refresh()
        }
    }

    private fun onItemClicked(item: Location) {
        sendShortToast("Clicked Location ${item.name}")
//        findNavController().navigate(CharactersFragmentDirections.showCharacterDetails(item.id, item.name))
    }
}