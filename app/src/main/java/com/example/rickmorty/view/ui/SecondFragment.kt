package com.example.rickmorty.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickmorty.databinding.FragmentSecondBinding
import com.example.rickmorty.view.adapter.CharacterFavoriteAdapter
import com.example.rickmorty.view.adapter.PlaceFavoriteAdapter
import com.example.rickmorty.viewModel.RMViewModel
import com.google.android.material.transition.MaterialContainerTransform

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: RMViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
        val charFavAdapter = CharacterFavoriteAdapter()
        val placeFavAdapter = PlaceFavoriteAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.showAllCharacterFav().observe(viewLifecycleOwner, Observer {
            binding.tvTitle.setText("Favorites")
            binding.recyclerView.adapter = charFavAdapter
            it?.let {
                charFavAdapter.update(it)
            }
        })

        viewModel.showAllPlaceFav().observe(viewLifecycleOwner, Observer {
            binding.tvTitle.setText("Favorites")
            binding.recyclerView.adapter = placeFavAdapter
            it?.let {
                placeFavAdapter.update(it)
            }
        })

        // Delete favs
        binding.btDelete.setOnClickListener {
            viewModel.deleteAllCharacterFav()
        }
    }
}