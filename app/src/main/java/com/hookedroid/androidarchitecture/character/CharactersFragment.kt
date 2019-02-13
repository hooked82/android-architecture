package com.hookedroid.androidarchitecture.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hookedroid.androidarchitecture.BaseFragment
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.data.NetworkState
import com.hookedroid.androidarchitecture.databinding.FragmentCharactersBinding
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : BaseFragment<CharactersViewModel>() {

    private lateinit var mBinding: FragmentCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
            refresh_layout.isRefreshing = it == NetworkState.LOADING
        })

        refresh_layout.setOnRefreshListener {
            mViewModel.refresh()
        }
    }

    fun onItemClicked(item: Character) {
        Toast.makeText(requireContext(), "Item Clicked: ${item.name}", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "CharactersFragment"
    }
}