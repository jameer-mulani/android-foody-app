package com.example.foodyappstefan2023.utils

object Constants {

    const val API_KEY = "65794f7d169d4fc891baef6539a519aa"
    const val BASE_URL = "https://api.spoonacular.com"


    //Recipe queries constants
    const val QUERY_TYPE = "apiKey"
    const val QUERY_API_KEY = "type"
    const val QUERY_DIET = "diet"
    const val QUERY_ADD_RECIPE_INFO = "addRecipeInformation"
    const val QUERY_FILL_INGREDIENTS = "fillIngredients"

    object ErrorCodes{
        const val NOT_INTERNET_CONNECTION = 10100
        const val NETWORK_TIMEOUT = NOT_INTERNET_CONNECTION + 1;
        const val SOMETHING_WENT_WRONG = NETWORK_TIMEOUT+1
        const val NO_RECIPE_FOUND = SOMETHING_WENT_WRONG +1
        const val API_LIMIT_EXCEED = NO_RECIPE_FOUND +1
        const val FAILED_TO_HANDLE_RESPONSE = API_LIMIT_EXCEED + 1

        fun getErrorPrefix(errorCode : Int) = "[Err:$errorCode]"
    }

}