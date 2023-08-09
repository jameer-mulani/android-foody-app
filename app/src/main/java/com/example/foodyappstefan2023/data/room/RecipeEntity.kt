package com.example.foodyappstefan2023.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodyappstefan2023.data.models.FoodRecipe
import com.example.foodyappstefan2023.utils.Constants


@Entity(tableName = Constants.RECIPE_TABLE)
class RecipeEntity(var foodRecipe: FoodRecipe) {

    @PrimaryKey(autoGenerate = false)
    var id : Int = 0

}