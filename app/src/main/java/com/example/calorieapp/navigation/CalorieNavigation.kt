package com.example.calorieapp.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calorieapp.model.CalorieAppViewModel
import com.example.calorieapp.LoginPage
import com.example.calorieapp.screens.MealsScreen
import com.example.calorieapp.screens.HomeScreen
import com.example.calorieapp.screens.ListScreen
import com.example.calorieapp.screens.RemindersScreen
import com.example.calorieapp.screens.SettingsScreen

@Composable
fun CalorieNavigation() {
    val navController = rememberNavController()
    val viewModel = viewModel<CalorieAppViewModel>()
    val sharedPreferences = LocalContext.current.getSharedPreferences("calorie_preferences", Context.MODE_PRIVATE)

    viewModel.getFoodList()
    NavHost(navController = navController, startDestination ="HomeScreen" ) {
        composable("HomeScreen") {
            HomeScreen(navController = navController, viewModel = viewModel, sharedPreferences)
        }
        composable("SettingsScreen"){
            SettingsScreen(navController = navController, sharedPreferences.edit())
        }
        composable("AddMealScreen"){
            MealsScreen(navController = navController, viewModel = viewModel)
        }
        composable("Reminders"){
            RemindersScreen(navController, viewModel)
        }
        composable("ListScreen"){
            ListScreen(navController, viewModel.foodList)
        }
    }
}

