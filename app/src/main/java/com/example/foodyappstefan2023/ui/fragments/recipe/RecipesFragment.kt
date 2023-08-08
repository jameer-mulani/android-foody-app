package com.example.foodyappstefan2023.ui.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyappstefan2023.adapters.RecipesAdapter
import com.example.foodyappstefan2023.databinding.FragmentReceipesBinding
import com.example.foodyappstefan2023.viewmodels.MainViewModel
import com.example.foodyappstefan2023.utils.Constants
import com.example.foodyappstefan2023.utils.NetworkResult
import com.example.foodyappstefan2023.viewmodels.RecipesViewModels


class RecipesFragment : Fragment() {


    private val mAdapter by lazy {
        RecipesAdapter()
    }

    private lateinit var binding: FragmentReceipesBinding

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModels: RecipesViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipesViewModels = ViewModelProvider(requireActivity())[RecipesViewModels::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReceipesBinding.inflate(inflater, container, false)
        setupRecyclerView()
        requestApiData()
        return binding.root;
    }

    private fun setupRecyclerView() {
        binding.recipesRv.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }
    }

    private fun requestApiData() {
//        mainViewModel.getRecipes(recipesViewModels.getRecipeQueries())
        mainViewModel.recipeResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        mAdapter.setData(it)
                    }
                }

                is NetworkResult.Loading -> {

                }

                is NetworkResult.Error -> {
                    if (response.message!!.contains(Constants.ErrorCodes.getErrorPrefix(Constants.ErrorCodes.NOT_INTERNET_CONNECTION))){
                        toggleErrorVisibility(true)
                    }else{
                        toggleErrorVisibility(false)
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun toggleErrorVisibility(showError: Boolean) {
        if (showError){
            binding.apply {
                errorLinearLayout.visibility = View.VISIBLE
                recipesRv.visibility = View.GONE
            }
        }else{
            binding.apply {
                errorLinearLayout.visibility = View.GONE
                recipesRv.visibility = View.VISIBLE
            }
        }
    }


}