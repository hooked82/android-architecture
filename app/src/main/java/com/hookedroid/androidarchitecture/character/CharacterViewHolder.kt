package com.hookedroid.androidarchitecture.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.databinding.ItemCharacterBinding

class CharacterViewHolder(private val mBinding: ItemCharacterBinding,
                          private val clickCallback: (Character) -> Unit)
    : RecyclerView.ViewHolder(mBinding.root) {

    fun onBind(item: Character?) {
        mBinding.viewModel = item

        item?.let {
            itemView.setOnClickListener { clickCallback(item) }
        }
    }

    fun clear() {
        mBinding.characterName.text = ""
        mBinding.characterOrigin.text = ""
        mBinding.characterSpecies.text = ""
        mBinding.characterStatus.text = ""
        //TODO - Once Placeholders are turned on, see if invalidate() would work here or best way of clearing the image
        mBinding.characterImage.setImageResource(0)
    }

    companion object {
        fun create(parent: ViewGroup, clickCallback: (Character) -> Unit): CharacterViewHolder {
            return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false), clickCallback)
        }
    }
}