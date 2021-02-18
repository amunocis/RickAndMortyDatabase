package com.example.rickmorty.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.databinding.CharacterItemBinding
import com.example.rickmorty.model.local.entities.Character

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterVH>() {

    private var listCharacter = listOf<Character>()
    private val selectedCharacter = MutableLiveData<Character>()

    fun update(list: List<Character>) {
        listCharacter = list
        notifyDataSetChanged()
    }

    fun selectedCharacter(): LiveData<Character> = selectedCharacter

    inner class CharacterVH(private val binding: CharacterItemBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(character: Character) {
            binding.tvCharacter.text = character.name
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedCharacter.value = listCharacter[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        return CharacterVH(CharacterItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.bind(listCharacter[position])
    }

    override fun getItemCount() = listCharacter.size
}