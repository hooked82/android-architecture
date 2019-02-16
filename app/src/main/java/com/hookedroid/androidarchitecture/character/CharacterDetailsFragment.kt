package com.hookedroid.androidarchitecture.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.hookedroid.androidarchitecture.BaseFragment
import com.hookedroid.androidarchitecture.databinding.FragmentCharacterDetailsBinding

class CharacterDetailsFragment : BaseFragment<CharacterDetailsViewModel>() {

    private lateinit var mBinding: FragmentCharacterDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentCharacterDetailsBinding.inflate(inflater, container, false).apply {
            mBinding = this
            setLifecycleOwner(this@CharacterDetailsFragment)
            return root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val characterId = CharacterDetailsFragmentArgs.fromBundle(arguments!!).characterId

        mViewModel.character.observe(this, Observer {
            mBinding.viewModel = it
        })

        mViewModel.showCharacter(characterId)
    }
}