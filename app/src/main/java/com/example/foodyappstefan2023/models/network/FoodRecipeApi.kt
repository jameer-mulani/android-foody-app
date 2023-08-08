package com.example.foodyappstefan2023.models.network

import com.example.foodyappstefan2023.models.data.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipeApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        //path queries
        @QueryMap queries: Map<String, String>
    ): Response<FoodRecipe>

}