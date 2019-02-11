package com.hookedroid.androidarchitecture.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hookedroid.androidarchitecture.BaseFragment
import com.hookedroid.androidarchitecture.adapter.BasePagedListAdapter
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.databinding.FragmentCharactersBinding

class CharactersFragment : BaseFragment<CharactersViewModel>(), BasePagedListAdapter.OnItemClickListener<Character> {

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

        val adapter = CharacterPagedListAdapter(requireContext())

        mBinding.charactersList.layoutManager = LinearLayoutManager(requireContext())
        mBinding.charactersList.adapter = adapter

        mViewModel.characters.observe(this, Observer {
            Log.d(TAG, "Observing Characters")
            adapter.submitList(it)
        })
        mViewModel.showCharacters(1)
    }

    override fun onItemClicked(item: Character) {
        Toast.makeText(requireContext(), "Item Clicked: ${item.name}", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "CharactersFragment"
    }
}