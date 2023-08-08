package com.example.foodyappstefan2023.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodyappstefan2023.R
import com.example.foodyappstefan2023.databinding.ActivityMainBinding
import com.example.foodyappstefan2023.models.MainViewModel
import com.example.foodyappstefan2023.models.network.RemoteDataSource
import com.example.foodyappstefan2023.utils.Constants
import com.example.foodyappstefan2023.utils.NetworkResult
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val contentView =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        mainViewModel.recipeResponse.observe(this, Observer {
            if (it is NetworkResult.Success) {
                Toast.makeText(
                    this,
                    "Success, result : ${it.data!!.results.size}",
                    Toast.LENGTH_SHORT
                ).show()



            }

            if (it is NetworkResult.Error) {
                Toast.makeText(this, "Error:${it.message}", Toast.LENGTH_SHORT).show()
            }

            if (it is NetworkResult.Loading) {
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            }
        })

        mainViewModel.getRecipes(
            mapOf(
                "apiKey" to Constants.API_KEY,
                "type" to "finger",
                "diet" to "vegan",
            )
        )

        navController = findNavController(R.id.navHostFragment)
        bottomNavigationView = contentView.bottomNavView
        bottomNavigationView.setupWithNavController(navController)


        //to reflect the fragment label into the default actionbar
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.receipesFragment,
                R.id.favoritesReceipes,
                R.id.foodJokeFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}