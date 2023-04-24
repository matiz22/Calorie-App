package com.example.calorieapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.calorieapp.components.ListOfMeals
import com.example.calorieapp.model.FoodList
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavHostController, foodList: StateFlow<List<FoodList>>) {
    val data = foodList.collectAsState().value
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "List") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="Back" )
                }}
                )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(top = it.calculateTopPadding())){
            items(data){
                ListOfMeals(foodList = it)
            }
        }
    }
}