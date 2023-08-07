package com.example.foodyappstefan2023

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)
        bottomNavigationView = findViewById(R.id.bottomNavView)
        bottomNavigationView.setupWithNavController(navController)


        //to reflect the fragment label into the default actionbar
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.receipesFragment,
            R.id.favoritesReceipes,
            R.id.foodJokeFragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)



    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}