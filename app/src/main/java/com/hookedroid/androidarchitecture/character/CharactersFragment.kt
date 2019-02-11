package com.hookedroid.androidarchitecture.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.hookedroid.androidarchitecture.BaseFragment
import com.hookedroid.androidarchitecture.databinding.FragmentCharactersBinding

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

        val adapter = CharacterPagedListAdapter(requireContext())

        mBinding.charactersList.adapter = adapter

        mViewModel.characters.observe(this, Observer { adapter.submitList(it) })
        mViewModel.showCharacters(1)
    }
}