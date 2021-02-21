package com.example.rickmorty.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.databinding.PlaceFavItemBinding
import com.example.rickmorty.model.local.entities.Places

class PlaceFavoriteAdapter: RecyclerView.Adapter<PlaceFavoriteAdapter.PlaceFavoriteVH>() {

    private var listPlace = listOf<Places>()
    private val selectedPlace = MutableLiveData<Places>()

    fun update(list: List<Places>) {
        listPlace = list
        notifyDataSetChanged()
    }

    inner class PlaceFavoriteVH(private val binding: PlaceFavItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(places: Places) {
            binding.tvPlaceName.text = places.name
            binding.tvPlaceType.text = places.type
            binding.tvPlaceDimension.text = places.dimension
            if (places.fav) {
                binding.ivFav.setColorFilter(Color.RED)
            } else {
                binding.ivFav.setColorFilter(Color.LTGRAY)
            }
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedPlace.value = listPlace[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceFavoriteVH {
        return PlaceFavoriteVH(PlaceFavItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount() = listPlace.size

    override fun onBindViewHolder(holder: PlaceFavoriteVH, position: Int) {
        holder.bind(listPlace[position])
    }
}