package com.example.calorieapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.calorieapp.model.CalorieAppViewModel
import com.example.calorieapp.model.Food

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddMeal(
    dialogState: MutableState<Boolean>,
    viewModel: CalorieAppViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val name = remember {
        mutableStateOf("")
    }
    val nameError = remember {
        mutableStateOf(false)
    }
    val portion = remember {
        mutableStateOf("")
    }
    val portionError = remember {
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

    AlertDialog(onDismissRequest = { dialogState.value = false }) {
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
                Text(text = "Add Food")
                Spacer(modifier = Modifier.size(20.dp))
                OutlinedTextField(
                    modifier = Modifier.padding(5.dp),
                    maxLines = 1,
                    value = name.value,
                    onValueChange = {
                        name.value = it
                        nameError.value = name.value.replace(" ", "") == ""
                    },
                    label = { Text(text = "Name") },
                    isError = nameError.value,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    })

                )
                OutlinedTextField(
                    modifier = Modifier.padding(5.dp),
                    maxLines = 1,
                    value = portion.value,
                    onValueChange = {
                        portion.value = it
                        try {
                            portionError.value = portion.value.toDouble() < 0.0
                        } catch (e: Exception) {
                            portionError.value = true
                        }
                    },
                    label = { Text(text = "Portion") },
                    isError = portionError.value,
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
                        if ((!(nameError.value && caloriesError.value && carbohydratesError.value && proteinError.value && fatError.value && portionError.value)
                                    && (name.value != ""
                                    && calories.value != ""
                                    && protein.value != ""
                                    && fat.value != ""
                                    && portion.value != "")
                                    )
                        ) {
                            viewModel.addFood(
                                Food(
                                    calories.value.toDouble(),
                                    carbohydrates.value.toDouble(),
                                    fat.value.toDouble(),
                                    name.value,
                                    protein.value.toDouble(),
                                    portion.value.toDouble()
                                )
                            )
                            dialogState.value =false
                        }
                    })
                )
                Spacer(modifier = Modifier.size(20.dp))
                OutlinedButton(
                    enabled = (!(nameError.value && caloriesError.value && carbohydratesError.value && proteinError.value && fatError.value && portionError.value)
                            && (name.value != ""
                            && calories.value != ""
                            && protein.value != ""
                            && fat.value != ""
                            && portion.value != "")
                            ),
                    onClick = {
                        viewModel.addFood(
                            Food(
                                calories.value.toDouble(),
                                carbohydrates.value.toDouble(),
                                fat.value.toDouble(),
                                name.value,
                                protein.value.toDouble(),
                                portion.value.toDouble()
                            )
                        )
                        dialogState.value = false
                    }) {
                    Text(text = "Save")
                }
            }
        }

    }
}