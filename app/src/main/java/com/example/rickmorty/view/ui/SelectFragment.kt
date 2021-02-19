package com.example.rickmorty.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.rickmorty.R
import com.example.rickmorty.databinding.SelectFragmentBinding
import com.example.rickmorty.viewModel.RMViewModel


class SelectFragment : Fragment() {

    private lateinit var binding: SelectFragmentBinding
    private val viewModel: RMViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SelectFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btCharacter.setOnClickListener {
            viewModel.selCat = 1
            findNavController().navigate(R.id.action_selectFragment_to_FirstFragment)
        }

        binding.btLocation.setOnClickListener {
            viewModel.selCat = 2
            findNavController().navigate(R.id.action_selectFragment_to_FirstFragment)
        }

    }



}