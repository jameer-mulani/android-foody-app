package com.example.foodyappstefan2023.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyappstefan2023.data.models.FoodRecipe
import com.example.foodyappstefan2023.data.repository.FoodRepository
import com.example.foodyappstefan2023.data.room.RecipeEntity
import com.example.foodyappstefan2023.utils.Constants.ErrorCodes
import com.example.foodyappstefan2023.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val foodRepository: FoodRepository
) : AndroidViewModel(application = application) {

    val recipeFromDatabase : LiveData<List<RecipeEntity>> = foodRepository.localDataSource.getRecipes().asLiveData()

    private val _recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    val recipeResponse = _recipesResponse

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        _recipesResponse.value = NetworkResult.Loading()
        try {
            if (hasInternetConnection()) {
                val response = foodRepository.remoteDataSource.getRecipes(queries)
                _recipesResponse.value = handleRecipesResponse(response)

                val foodRecipe = _recipesResponse.value?.data
                if (foodRecipe != null){
                    cacheRecipes(foodRecipe)
                }

            } else {
                _recipesResponse.value = NetworkResult.Error(
                    "No internet connection ${
                        ErrorCodes.getErrorPrefix(
                            ErrorCodes.NOT_INTERNET_CONNECTION
                        )
                    }"
                )
            }
        } catch (e: Exception) {
            _recipesResponse.value = NetworkResult.Error(
                "Something went wrong, please try again! ${
                    ErrorCodes.getErrorPrefix(ErrorCodes.SOMETHING_WENT_WRONG)
                }"
            )
        }

    }

    private fun cacheRecipes(foodRecipe: FoodRecipe) {
        val recipeEntity = RecipeEntity(foodRecipe)
        insertRecipes(recipeEntity)
    }

    private fun insertRecipes(recipeEntity: RecipeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            foodRepository.localDataSource.insertRecipes(recipeEntity)
        }
    }

    private fun handleRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
        when {
            response.message().contains("timeout") -> return NetworkResult.Error("Timeout ${ErrorCodes.getErrorPrefix(ErrorCodes.NETWORK_TIMEOUT)}")
            response.code() == 402 -> return NetworkResult.Error("Api Limit exceeded ${ErrorCodes.getErrorPrefix(ErrorCodes.API_LIMIT_EXCEED)}")
            response.code() == 401 -> return NetworkResult.Error("Not Authorized ${ErrorCodes.getErrorPrefix(ErrorCodes.NOT_UNAUTHORIZED)}")
            response.body() == null -> return NetworkResult.Error("No recipe to found ${ErrorCodes.getErrorPrefix(ErrorCodes.NO_RECIPE_FOUND)}")
            response.body()!!.results.isNullOrEmpty() -> return NetworkResult.Error("No recipes found ${ErrorCodes.getErrorPrefix(ErrorCodes.NO_RECIPE_FOUND)}")
            response.isSuccessful -> return NetworkResult.Success(data = response.body()!!)
        }
        return NetworkResult.Error("Failed to handle response ${ErrorCodes.getErrorPrefix(ErrorCodes.FAILED_TO_HANDLE_RESPONSE)}")
    }


    private fun hasInternetConnection(): Boolean {

        val connectivityManager: ConnectivityManager =
            getApplication<Application>().getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

    }


}