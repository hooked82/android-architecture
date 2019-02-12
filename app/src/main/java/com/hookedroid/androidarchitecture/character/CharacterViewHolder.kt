package com.hookedroid.androidarchitecture.character

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hookedroid.androidarchitecture.adapter.BasePagedListAdapter
import com.hookedroid.androidarchitecture.adapter.BaseViewHolder
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.databinding.ItemCharacterBinding

class CharacterViewHolder(private val mBinding: ItemCharacterBinding)
    : BaseViewHolder<Character, BasePagedListAdapter.OnItemClickListener<Character>>(mBinding.root) {

    override fun onBind(item: Character?, listener: BasePagedListAdapter.OnItemClickListener<Character>?) {
        mBinding.viewModel = item

        item?.let {
            listener?.let {
                itemView.setOnClickListener { listener.onItemClicked(item) }
            }
        }
    }

    override fun clear() {
        mBinding.characterName.text = ""
        mBinding.characterOrigin.text = ""
        mBinding.characterSpecies.text = ""
        mBinding.characterStatus.text = ""
        //TODO - Once Placeholders are turned on, see if invalidate() would work here or best way of clearing the image
        mBinding.characterImage.setImageResource(0)
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
            return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
}