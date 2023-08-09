package com.example.foodyappstefan2023.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodyappstefan2023.data.room.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipeEntity: RecipeEntity)

    @Query("SELECT * FROM RECIPE_TABLE ORDER BY id ASC")
    fun readRecipes() : Flow<List<RecipeEntity>>


}