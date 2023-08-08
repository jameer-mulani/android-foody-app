package com.example.foodyappstefan2023.ui.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyappstefan2023.R
import com.example.foodyappstefan2023.adapters.RecipesAdapter
import com.example.foodyappstefan2023.databinding.FragmentReceipesBinding
import com.example.foodyappstefan2023.databinding.RecipesListItemBinding
import com.example.foodyappstefan2023.models.data.models.Result


class RecipesFragment : Fragment() {


    private lateinit var binding: FragmentReceipesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReceipesBinding.inflate(inflater, container, false)
        binding.recipesRv.adapter = RecipesAdapter()
        binding.recipesRv.layoutManager = LinearLayoutManager(requireActivity())
        binding.recipesRv.setHasFixedSize(true)
        return binding.root;
    }

}