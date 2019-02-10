package com.hookedroid.androidarchitecture.character

import com.hookedroid.androidarchitecture.adapter.BasePagedListAdapter
import com.hookedroid.androidarchitecture.adapter.BaseViewHolder
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.databinding.ItemCharacterBinding

class CharacterViewHolder(private val mBinding: ItemCharacterBinding)
    : BaseViewHolder<Character, BasePagedListAdapter.OnItemClickListener<Character>>(mBinding.root) {

    override fun onBind(item: Character, listener: BasePagedListAdapter.OnItemClickListener<Character>?) {
        mBinding.viewModel = item

        listener?.let { itemView.setOnClickListener { listener.onItemClicked(item) } }
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}