package com.hookedroid.androidarchitecture.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hookedroid.androidarchitecture.BaseFragment
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.data.NetworkState
import com.hookedroid.androidarchitecture.databinding.FragmentCharactersBinding

class CharactersFragment : BaseFragment<CharactersViewModel>() {

    private lateinit var mBinding: FragmentCharactersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentCharactersBinding.inflate(inflater, container, false).apply {
            mBinding = this
            setLifecycleOwner(this@CharactersFragment)
            return root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initSwipeToRefresh()

        val adapter = CharacterPagedListAdapter(
            { onItemClicked(it) },
            { mViewModel.retry() }
        )

        mBinding.charactersList.layoutManager = LinearLayoutManager(requireContext())
        mBinding.charactersList.adapter = adapter

        mViewModel.characters.observe(this, Observer {
            adapter.submitList(it)
        })
        mViewModel.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })
        mViewModel.showCharacters(1)
    }

    private fun initSwipeToRefresh() {
        mViewModel.refreshState.observe(this, Observer {
            mBinding.refreshLayout.isRefreshing = it == NetworkState.LOADING
        })

        mBinding.refreshLayout.setOnRefreshListener {
            mViewModel.refresh()
        }
    }

    private fun onItemClicked(item: Character) {
        findNavController().navigate(CharactersFragmentDirections.showCharacterDetails(item.id, item.name))
    }
}