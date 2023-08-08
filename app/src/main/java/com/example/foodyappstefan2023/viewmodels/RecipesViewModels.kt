package com.example.foodyappstefan2023.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.foodyappstefan2023.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesViewModels @Inject constructor(application: Application) : AndroidViewModel(application) {


    fun getRecipeQueries(): Map<String, String> {
        return mapOf(
            Constants.QUERY_API_KEY to Constants.API_KEY,
            Constants.QUERY_TYPE to "snack",
            Constants.QUERY_DIET to "vegan",
            Constants.QUERY_ADD_RECIPE_INFO to "true",
            Constants.QUERY_FILL_INGREDIENTS to "true"
        )
    }


}