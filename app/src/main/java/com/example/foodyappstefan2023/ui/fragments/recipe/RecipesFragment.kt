package com.example.foodyappstefan2023.ui.fragments.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyappstefan2023.R
import com.example.foodyappstefan2023.adapters.RecipesAdapter
import com.example.foodyappstefan2023.databinding.FragmentReceipesBinding
import com.example.foodyappstefan2023.viewmodels.MainViewModel
import com.example.foodyappstefan2023.utils.Constants
import com.example.foodyappstefan2023.utils.NetworkResult
import com.example.foodyappstefan2023.utils.observeOnce
import com.example.foodyappstefan2023.viewmodels.RecipesViewModels
import kotlinx.coroutines.launch


class RecipesFragment : Fragment() {


    private val mAdapter by lazy {
        RecipesAdapter()
    }

    private var _binding: FragmentReceipesBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentReceipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        binding.recipesFab.setOnClickListener {
            findNavController().navigate(R.id.action_receipesFragment_to_recipeBottomSheet)
        }

        setupRecyclerView()
        requestRecipeData()
        return binding.root;
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.recipesRv.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }
    }

    private fun reachCacheData(){
        lifecycleScope.launch {
            mainViewModel.recipeFromDatabase.observe(viewLifecycleOwner){
                if (it.isNotEmpty()){
                    mAdapter.setData(it[0].foodRecipe)
                }
            }
        }
    }


    private fun requestRecipeData(){
       lifecycleScope.launch {
           mainViewModel.recipeFromDatabase.observeOnce(viewLifecycleOwner) { localRecipes ->
               if (localRecipes.isNotEmpty()) {
                   Log.d("RecipesFragment", "database request")
                   mAdapter.setData(localRecipes[0].foodRecipe)
               } else {
                   requestApiData()
               }

           }
       }
    }

    private fun requestApiData() {

        Log.d("RecipesFragment", "api request")

        mainViewModel.getRecipes(recipesViewModels.getRecipeQueries())
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
                    reachCacheData()
                }
            }
        }
    }


}