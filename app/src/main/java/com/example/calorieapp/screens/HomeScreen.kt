@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.calorieapp.screens

import DailyWidget
import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

import com.example.calorieapp.components.AppBar
import com.example.calorieapp.components.BottomBar
import com.example.calorieapp.components.Drawer
import com.example.calorieapp.components.ListOfMeals
import com.example.calorieapp.model.CalorieAppViewModel
import com.himanshoe.charty.circle.model.CircleData
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: CalorieAppViewModel,
    sharedPreferences: SharedPreferences
) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val correctFoodList = viewModel.foodList.collectAsState(emptyList()).value.filter {
        SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ).format(it.date) == SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ).format(Date())
    }
    var kcalSum = 0.0
    var fatSum = 0.0
    var proteinSum = 0.0
    var carboSum = 0.0

    val kcalLimit = rememberUpdatedState(sharedPreferences.getFloat("calorie", 0f))
    val fatLimit = rememberUpdatedState(sharedPreferences.getFloat("fat", 0f))
    val carboLimit = rememberUpdatedState(sharedPreferences.getFloat("carbohydrates", 0f))
    val proteinLimit = rememberUpdatedState(sharedPreferences.getFloat("protein", 0f))

    correctFoodList.forEach {
        it.items.forEach { food ->
            kcalSum += food.calories
            fatSum += food.fat_total_g
            proteinSum += food.protein_g
            carboSum += food.carbohydrates_total_g
        }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home Screen"
                        )
                    }, label = { Text(text = "Home Screen") }, selected = false, onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    })
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "Home Screen"
                        )
                    }, label = { Text(text = " Screen") }, selected = false, onClick = {
                        navController.navigate("ListScreen")
                    })
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "AddMeal Screen"
                        )
                    }, label = { Text(text = "Add Meal Screen") }, selected = false, onClick = {
                        navController.navigate("AddMealScreen")
                    })
            }
        }) {
        Scaffold(
            topBar = {
                AppBar(navController = navController, openDrawer = drawerState)
            },
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                        top = paddingValues.calculateTopPadding()
                    )
                    .verticalScroll(rememberScrollState())
            ) {

                DailyWidget(
                    fatSum, fatLimit.value.toDouble(),
                    carboSum, carboLimit.value.toDouble(),
                    proteinSum, proteinLimit.value.toDouble(),
                    kcalSum, kcalLimit.value.toDouble(),
                    circleData = listOf(
                        CircleData(
                            xValue = 1.0f,
                            yValue = 0.5f,
                            color = Color.Transparent
                        ),
                        CircleData(
                            xValue = 1.0f,
                            yValue = fatSum.toFloat() / fatLimit.value,
                            color = Color.Yellow
                        ),
                        CircleData(
                            xValue = 1.0f,
                            yValue = proteinSum.toFloat() / proteinLimit.value,
                            color = Color.Blue
                        ),
                        CircleData(
                            xValue = 1.0f,
                            yValue = carboSum.toFloat() / carboLimit.value,
                            color = Color.Green
                        ),
                        CircleData(
                            xValue = 1.0f,
                            yValue = kcalSum.toFloat() / kcalLimit.value,
                            color = Color.Red
                        ),

                        )

                )
                Column(

                ) {
                    correctFoodList.forEach {
                        ListOfMeals(foodList = it)
                    }
                }

            }
        }

    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomesScreenPreview() {
//    val nav = rememberNavController( )
//    HomeScreen(navController = nav)
}