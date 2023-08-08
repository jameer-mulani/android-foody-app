package com.example.foodyappstefan2023.models.network

import com.example.foodyappstefan2023.models.data.models.FoodRecipe
import com.example.foodyappstefan2023.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(val foodRecipeApi: FoodRecipeApi) {


    /*
    *
    * https://api.spoonacular.com/recipes/complexSearch?number=1&apiKey=65794f7d169d4fc891baef6539a519aa&type=finger%20food&diet=vegan
    * */

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipeApi.getRecipes(queries);
    }


//    fun getRecipes(){
//        CoroutineScope(Dispatchers.IO).launch {
//            val body = foodRecipeApi.getRecipes(
//                mapOf(
//                    "apiKey" to Constants.API_KEY,
//                    "type" to "finger",
//                    "diet" to "vegan",
//                )
//            ).body()
//
//            print("response : $body")
//        }
//    }

}