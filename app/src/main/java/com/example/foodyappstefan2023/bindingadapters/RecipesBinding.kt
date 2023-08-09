package com.example.foodyappstefan2023.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foodyappstefan2023.data.models.FoodRecipe
import com.example.foodyappstefan2023.data.room.RecipeEntity
import com.example.foodyappstefan2023.utils.NetworkResult

class RecipesBinding {

    companion object {


        @JvmStatic
        @BindingAdapter("apiResult", "databaseResult", requireAll = true)
        fun errorImageWhenError(
            imageView: ImageView,
            networkResult: NetworkResult<FoodRecipe>?,
            databaseResults: List<RecipeEntity>?
        ) {
            if (networkResult is NetworkResult.Error && databaseResults.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            } else if (networkResult is NetworkResult.Loading) {
                imageView.visibility = View.GONE
            } else if (networkResult is NetworkResult.Success) {
                imageView.visibility = View.GONE
            }
        }


        @JvmStatic
        @BindingAdapter("apiResult2", "databaseResult2", requireAll = true)
        fun errorTextWhenError(
            textView: TextView,
            networkResult: NetworkResult<FoodRecipe>?,
            databaseResults: List<RecipeEntity>?
        ) {
            if (networkResult is NetworkResult.Error && databaseResults.isNullOrEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = networkResult.message.toString()
            } else if (networkResult is NetworkResult.Loading) {
                textView.visibility = View.GONE
            } else if (networkResult is NetworkResult.Success) {
                textView.visibility = View.GONE
            }
        }

    }
}