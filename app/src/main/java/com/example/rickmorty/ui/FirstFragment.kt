package com.example.rickmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickmorty.R
import com.example.rickmorty.databinding.FragmentFirstBinding
import com.example.rickmorty.ui.adapter.CharacterAdapter
import com.example.rickmorty.viewModel.RMViewModel

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
        val adapter = CharacterAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.getCharacterList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })


    }
}