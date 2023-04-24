package com.example.calorieapp.screens

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    sharedPreferences: SharedPreferences.Editor
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val showAlert = remember {
        mutableStateOf(false)
    }
    val calories = remember {
        mutableStateOf("")
    }
    val caloriesError = remember {
        mutableStateOf(false)
    }
    val carbohydrates = remember {
        mutableStateOf("")
    }
    val carbohydratesError = remember {
        mutableStateOf(false)
    }
    val fat = remember {
        mutableStateOf("")
    }
    val fatError = remember {
        mutableStateOf(false)
    }
    val protein = remember {
        mutableStateOf("")
    }
    val proteinError = remember {
        mutableStateOf(false)
    }

    if (showAlert.value) {
        AlertDialog(onDismissRequest = {
            showAlert.value = false
        }) {
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
                    OutlinedTextField(
                        modifier = Modifier.padding(5.dp),
                        maxLines = 1,
                        value = calories.value,
                        onValueChange = {
                            calories.value = it
                            try {
                                caloriesError.value = calories.value.toDouble() < 0.0
                            } catch (e: Exception) {
                                caloriesError.value = true
                            }
                        },
                        label = { Text(text = "Calories") },
                        isError = caloriesError.value,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        })
                    )
                    OutlinedTextField(
                        modifier = Modifier.padding(5.dp),
                        maxLines = 1,
                        value = carbohydrates.value,
                        onValueChange = {
                            carbohydrates.value = it
                            try {
                                carbohydratesError.value = carbohydrates.value.toDouble() < 0.0
                            } catch (e: Exception) {
                                carbohydratesError.value = true
                            }
                        },
                        label = { Text(text = "Carbohydrates") },
                        isError = carbohydratesError.value,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        })
                    )
                    OutlinedTextField(
                        modifier = Modifier.padding(5.dp),
                        value = fat.value,
                        maxLines = 1,
                        onValueChange = {
                            fat.value = it
                            try {
                                fatError.value = fat.value.toDouble() < 0.0
                            } catch (e: Exception) {
                                fatError.value = true
                            }
                        },
                        label = { Text(text = "Fat") },
                        isError = fatError.value,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        })
                    )
                    OutlinedTextField(
                        modifier = Modifier.padding(5.dp),
                        value = protein.value,
                        maxLines = 1,
                        onValueChange = {
                            protein.value = it
                            try {
                                proteinError.value = protein.value.toDouble() < 0.0
                            } catch (e: Exception) {
                                proteinError.value = true
                            }

                        },
                        label = { Text(text = "Protein") },
                        isError = proteinError.value,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            keyboardController?.hide()
                        })
                    )
                    OutlinedButton(onClick = {
                        try {
                            sharedPreferences.putFloat("calorie", calories.value.toFloat())
                            sharedPreferences.putFloat("protein", protein.value.toFloat())
                            sharedPreferences.putFloat("carbohydrates", carbohydrates.value.toFloat())
                            sharedPreferences.putFloat("fat", fat.value.toFloat())
                            sharedPreferences.apply()
                            showAlert.value = false
                        }catch (e:Exception){
                            Toast.makeText(context, "If you don't want set limit for one value insert 0", Toast.LENGTH_LONG).show()
                        }
                    }) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Settings") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(16.dp)
                        .clickable {
                            navController.navigate("Reminders")
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Reminders", fontSize = 20.sp)
                    }

                }
            }
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(16.dp)
                        .clickable {
                            showAlert.value = true
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Set limits", fontSize = 20.sp)
                    }
                }
            }
        }

    }
}
