package com.hookedroid.androidarchitecture.character

import android.content.Context
import android.view.ViewGroup
import com.hookedroid.androidarchitecture.R
import com.hookedroid.androidarchitecture.adapter.BasePagedListAdapter
import com.hookedroid.androidarchitecture.api.model.Character
import com.hookedroid.androidarchitecture.databinding.ItemCharacterBinding

class CharacterPagedListAdapter(context: Context)
    : BasePagedListAdapter<Character, BasePagedListAdapter.OnItemClickListener<Character>, CharacterViewHolder>(
    context,
    Character.diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(inflateBinding(R.layout.item_character, parent) as ItemCharacterBinding)
    }
}