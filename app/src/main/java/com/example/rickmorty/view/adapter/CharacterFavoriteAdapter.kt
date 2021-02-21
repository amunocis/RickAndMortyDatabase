package com.example.rickmorty.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmorty.databinding.CharacterFavItemBinding
import com.example.rickmorty.model.local.entities.Character

class CharacterFavoriteAdapter: RecyclerView.Adapter<CharacterFavoriteAdapter.CharacterFavoriteVH>() {

    private var listCharacter = listOf<Character>()
    private val selectedCharacter = MutableLiveData<Character>()

    fun update(list: List<Character>) {
        listCharacter = list
        notifyDataSetChanged()
    }

    inner class CharacterFavoriteVH(private val binding: CharacterFavItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(character: Character) {
            binding.tvCharacter.text = character.name
            binding.tvSpecies.text = character.species
            Glide.with(binding.root).load(character.image).into(binding.ivCharImage)
            if (character.fav) {
                binding.ivFav.setColorFilter(Color.RED)
            } else {
                binding.ivFav.setColorFilter(Color.LTGRAY)
            }
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedCharacter.value = listCharacter[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterFavoriteVH {
        return CharacterFavoriteVH(CharacterFavItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listCharacter.size

    override fun onBindViewHolder(holder: CharacterFavoriteVH, position: Int) {
        holder.bind(listCharacter[position])
    }
}

