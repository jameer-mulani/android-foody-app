package com.example.foodyappstefan2023.data.local

import com.example.foodyappstefan2023.data.room.RecipeEntity
import com.example.foodyappstefan2023.data.room.dao.RecipeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao
) {

    fun getRecipes() : Flow<List<RecipeEntity>>{
        return recipeDao.readRecipes()
    }

    suspend fun insertRecipes(recipeEntity: RecipeEntity){
        recipeDao.insertRecipes(recipeEntity)
    }

}