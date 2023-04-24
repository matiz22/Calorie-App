package com.example.calorieapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorieapp.model.CalorieAppViewModel
import com.example.calorieapp.model.Food


@Composable
fun FoodSearchResult(
    food: Food, viewModel: CalorieAppViewModel
) {
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    val gramsState: MutableState<String> = remember {
        mutableStateOf("0")
    }

    if (dialogState.value) {
        EditPortion(dialogState = dialogState, grams = gramsState) {
            viewModel.editPortion(
                food = food,
                newGram = gramsState.value.toDouble()
            )
        }
    }
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = food.name.capitalize(), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Row() {
                    IconButton(onClick = {dialogState.value = true }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit values")
                    }
                    IconButton(onClick = { viewModel.deleteFood(food) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Edit values")
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Portion:")
                    Text(text = food.serving_size_g.toString() + " g")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Kcal:")
                    Text(text = food.calories.toString())
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Carbohydrates:")
                    Text(text = food.carbohydrates_total_g.toString() + " g")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Protein:")
                    Text(text = food.protein_g.toString() + " g")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Fat:")
                    Text(text = food.fat_total_g.toString() + " g")
                }
            }

        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FoodPreview() {
//    FoodSearchResult(
//        item = Item(10.0, 20.0, 2, 20.0, 20.0, 29.0, "Deser", 2, 19.0, 10.0, 1, 20.0),
//        {},
//        MutableState(true))
}