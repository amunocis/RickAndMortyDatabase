package com.example.rickmorty.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmorty.R
import com.example.rickmorty.databinding.CharacterItemBinding
import com.example.rickmorty.model.local.entities.Character

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterVH>() {

    private var listCharacter = listOf<Character>()
    private val selectedCharacter = MutableLiveData<Character>()

    lateinit var context: Context
    private var lastPosition = -1

    fun update(list: List<Character>) {
        listCharacter = list
        notifyDataSetChanged()
    }

    fun selectedCharacter(): LiveData<Character> = selectedCharacter

    inner class CharacterVH(private val binding: CharacterItemBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var animation = AnimationUtils.loadAnimation(context, R.anim.slide_from_outside)

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
            itemView.startAnimation(animation)
        }
        override fun onClick(v: View?) {
            selectedCharacter.value = listCharacter[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        context = parent.context
        return CharacterVH(CharacterItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        if (holder.adapterPosition > lastPosition) {
            holder.bind(listCharacter[position])
            lastPosition = holder.adapterPosition
        }

    }

    override fun getItemCount() = listCharacter.size
}