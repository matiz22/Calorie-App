package com.example.calorieapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPortion(
    dialogState: MutableState<Boolean>,
    grams: MutableState<String>,
    editItem: () -> Unit
) {
    val errorTextField = remember {
        mutableStateOf(true)
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
                Text(text = "Edit portion")
                Spacer(modifier = Modifier.size(20.dp))
                TextField(
                    isError = errorTextField.value,
                    value = grams.value,
                    onValueChange = {
                        grams.value = it
                        try {
                            errorTextField.value = it.toDouble() < 0.0

                        } catch (e: Exception) {
                            errorTextField.value = true
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.size(20.dp))
                OutlinedButton(
                    enabled = !errorTextField.value,
                    onClick = {
                        editItem()
                        dialogState.value = false
                        grams.value = "0"
                    }) {
                    Text(text = "Save")
                }
            }
        }

    }
}