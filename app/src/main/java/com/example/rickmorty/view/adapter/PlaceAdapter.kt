package com.example.rickmorty.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.databinding.PlaceItemBinding
import com.example.rickmorty.model.local.entities.Places

class PlaceAdapter: RecyclerView.Adapter<PlaceAdapter.PlacesVH>() {

    private var listPlaces = listOf<Places>()
    private val selectedPlaces = MutableLiveData<Places>()

    fun update(list: List<Places>) {
        listPlaces = list
        notifyDataSetChanged()
    }

    fun selectedPlaces(): LiveData<Places> = selectedPlaces

    inner class PlacesVH(private val binding: PlaceItemBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(place: Places) {
            binding.tvPlaceName.text = place.name
            binding.tvPlaceType.text = place.type
            binding.tvPlaceDimension.text = place.dimension
            if (place.fav) {
                binding.ivFav.setColorFilter(Color.RED)
            } else {
                binding.ivFav.setColorFilter(Color.LTGRAY)
            }
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedPlaces.value = listPlaces[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesVH {
        return PlacesVH(PlaceItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PlacesVH, position: Int) {
        holder.bind(listPlaces[position])
    }

    override fun getItemCount() = listPlaces.size

}