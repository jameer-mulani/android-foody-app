package com.example.foodyappstefan2023.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodyappstefan2023.data.room.converters.RecipesTypeConverter
import com.example.foodyappstefan2023.data.room.dao.RecipeDao

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipeDatabase : RoomDatabase(){

    abstract fun recipesDao() : RecipeDao

}