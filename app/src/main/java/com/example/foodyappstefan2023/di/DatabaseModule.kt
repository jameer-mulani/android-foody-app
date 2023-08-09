package com.example.foodyappstefan2023.di

import android.content.Context
import androidx.room.Room
import com.example.foodyappstefan2023.data.room.RecipeDatabase
import com.example.foodyappstefan2023.data.room.dao.RecipeDao
import com.example.foodyappstefan2023.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun getRecipesDatabase(
        @ApplicationContext context: Context
    ): RecipeDatabase =
        Room.databaseBuilder(
            context, RecipeDatabase::class.java, Constants.DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun getRecipesDao(recipeDatabase: RecipeDatabase): RecipeDao = recipeDatabase.recipesDao()

}