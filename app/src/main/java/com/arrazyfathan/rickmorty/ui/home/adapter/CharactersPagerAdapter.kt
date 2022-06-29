package com.arrazyfathan.rickmorty.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arrazyfathan.rickmorty.R
import com.arrazyfathan.rickmorty.data.remote.response.SingleCharacterResponse
import com.arrazyfathan.rickmorty.databinding.AdapterCharactersBinding
import com.squareup.picasso.Picasso

class CharactersPagerAdapter :
    PagingDataAdapter<SingleCharacterResponse, CharactersPagerAdapter.CharactersViewHolder>(
        Comparator
    ) {

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val characters = getItem(position)!!
        holder.view.characterName.text = characters.name
        Picasso.get()
            .load(characters.image)
            .into(holder.view.characterImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCharactersBinding.inflate(inflater, parent, false)
        return CharactersViewHolder(binding)
    }

    class CharactersViewHolder(val view: AdapterCharactersBinding) :
        RecyclerView.ViewHolder(view.root)

    object Comparator : DiffUtil.ItemCallback<SingleCharacterResponse>() {
        override fun areItemsTheSame(
            oldItem: SingleCharacterResponse,
            newItem: SingleCharacterResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SingleCharacterResponse,
            newItem: SingleCharacterResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
}
