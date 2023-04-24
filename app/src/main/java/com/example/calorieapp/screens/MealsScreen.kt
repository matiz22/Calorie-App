package com.example.calorieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.calorieapp.model.CalorieAppViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.calorieapp.components.AddMeal
import com.example.calorieapp.components.EditPortion
import com.example.calorieapp.components.FoodSearchResult


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MealsScreen(navController: NavController, viewModel: CalorieAppViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchField by remember {
        mutableStateOf("")
    }
    val data = viewModel.queryResult.collectAsState().value

    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    val gramsState: MutableState<String> = remember {
        mutableStateOf("0")
    }
    val addingState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    val savingState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    val nameState: MutableState<String> = remember {
        mutableStateOf("")
    }


    Scaffold(
        bottomBar = {
            BottomAppBar() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    FloatingActionButton(onClick = { addingState.value = true }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }

                    FloatingActionButton(onClick = {
                        savingState.value = true
                    }) {
                        Icon(imageVector = Icons.Default.Done, contentDescription = "Save")
                    }
                }
            }

        },
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }, colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary
            ), actions = {
                IconButton(onClick = {
                    viewModel.getSearchedFood(searchField)
                    if (keyboardController != null) {
                        keyboardController.hide()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }, title = {
                TextField(
                    placeholder = {
                        Text(text = "Search", color = MaterialTheme.colorScheme.onPrimary)
                    },
                    value = searchField,
                    onValueChange = {
                        searchField = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        cursorColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        selectionColors = TextSelectionColors(
                            handleColor = MaterialTheme.colorScheme.onPrimary,
                            backgroundColor = MaterialTheme.colorScheme.onBackground
                        )
                    ),
                )
            })
        }) {
        if (savingState.value) {
            AlertDialog(onDismissRequest = { savingState.value = false }) {
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colorScheme.inversePrimary,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Save Meal")
                        Spacer(modifier = Modifier.size(20.dp))
                        TextField(value = nameState.value, onValueChange = {
                            nameState.value = it
                        })
                        Spacer(modifier = Modifier.size(20.dp))
                        OutlinedButton(onClick = {
                            viewModel.addFoodList(nameState.value)
                            viewModel.getFoodList()
                            savingState.value = false
                            navController.popBackStack()
                        }) {
                            Text(text = "Save")
                        }

                    }
                }

            }
        }
        if (addingState.value) {
            AddMeal(dialogState = addingState, viewModel = viewModel)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(), bottom = it.calculateBottomPadding()
                )
        ) {
            items(data.items) { food ->
                FoodSearchResult(
                    food = food,
                    viewModel = viewModel
                )
            }
        }
    }
}