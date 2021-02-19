package com.example.rickmorty.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickmorty.databinding.FragmentFirstBinding
import com.example.rickmorty.view.adapter.CharacterAdapter
import com.example.rickmorty.view.adapter.PlaceAdapter
import com.example.rickmorty.viewModel.RMViewModel
import com.google.android.material.transition.MaterialContainerTransform

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: RMViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
        val charAdapter = CharacterAdapter()
        val placeAdapter = PlaceAdapter()
        //binding.recyclerView.adapter = charAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        when(viewModel.selCat) {
            1 ->
                viewModel.getCharacterList().observe(viewLifecycleOwner, Observer {
                    binding.tvTitle.setText("Characters")
                    binding.recyclerView.adapter = charAdapter
                    it?.let {
                        charAdapter.update(it)
                    }
                })
            2 ->
                viewModel.getPlacesList().observe(viewLifecycleOwner, Observer {
                    binding.tvTitle.setText("Locations")
                    binding.recyclerView.adapter = placeAdapter
                    it?.let {
                        placeAdapter.update(it)
                    }
                })
        }

        charAdapter.selectedCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it.fav) {
                    it.fav = false
                    viewModel.updateCharFav(it)
                } else {
                    it.fav = true
                    viewModel.updateCharFav(it)
                }
            }
        })
        binding.btDelete.setOnClickListener {
            viewModel.deleteAllCharacterFav()
        }
    }
}