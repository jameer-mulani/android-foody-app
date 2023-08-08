package com.example.foodyappstefan2023.models

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyappstefan2023.models.data.models.FoodRecipe
import com.example.foodyappstefan2023.models.repository.FoodRepository
import com.example.foodyappstefan2023.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val foodRepository: FoodRepository
) : AndroidViewModel(application = application) {


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
            } else {
                _recipesResponse.value = NetworkResult.Error("No internet connection")
            }
        } catch (e: Exception) {
            _recipesResponse.value = NetworkResult.Error("Something went wrong, please try again!")
        }

    }

    private fun handleRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
        when {
            response.message().contains("timeout") -> return NetworkResult.Error("Timeout")
            response.code() == 402 -> return NetworkResult.Error("Api Limit exceeded")
            response.body() == null -> return NetworkResult.Error("No recipe to found")
            response.body()!!.results.isNullOrEmpty() -> return NetworkResult.Error("No recipes found")
            response.isSuccessful -> return NetworkResult.Success(data = response.body()!!)
        }
        return NetworkResult.Error("Failed to handle response")
    }


    fun hasInternetConnection(): Boolean {

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