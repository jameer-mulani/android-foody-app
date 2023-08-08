package com.example.foodyappstefan2023.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyappstefan2023.databinding.RecipesListItemBinding
import com.example.foodyappstefan2023.models.data.models.FoodRecipe
import com.example.foodyappstefan2023.models.data.models.Result
import com.example.foodyappstefan2023.utils.RecipeDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    private var recipes: List<Result> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    class RecipeViewHolder(val binding: RecipesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = RecipesListItemBinding.inflate(inflater, parent, false)
                return RecipeViewHolder(binding)
            }
        }

        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
        }

    }


    fun setData(foodRecipe: FoodRecipe) {
        val recipeDiffUtil = RecipeDiffUtil(recipes, foodRecipe.results)
        val recipeDiffUtilResult = DiffUtil.calculateDiff(recipeDiffUtil)
        this.recipes = foodRecipe.results
        recipeDiffUtilResult.dispatchUpdatesTo(this)
//        notifyDataSetChanged()
    }
}
