package com.example.calorieapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorieapp.model.FoodList
import com.example.calorieapp.model.Food
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ListOfMeals(foodList: FoodList) {
    var kcalSum = 0.0
    var fatSum = 0.0
    var proteinSum = 0.0
    var carboSum = 0.0
    var isExpanded = remember {
        mutableStateOf(false)
    }
    foodList.items.forEach { food ->
        kcalSum += food.calories
        fatSum += food.fat_total_g
        proteinSum += food.protein_g
        carboSum += food.carbohydrates_total_g
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .animateContentSize(
                animationSpec = tween(
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(text = foodList.name, fontSize = 25.sp, modifier = Modifier.padding(5.dp))
                    Text(
                        text = SimpleDateFormat(
                            "yyyy-MM-dd",
                            Locale.getDefault()
                        ).format(foodList.date)
                    )

                }
                IconButton(onClick = { isExpanded.value = !isExpanded.value }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Expand")
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "Summary", modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "kcal:")
                    Text(text = String.format("%.2f", kcalSum))
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Fat")
                    Text(text = String.format("%.2f", fatSum))
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Protein")
                    Text(text = String.format("%.2f", proteinSum))
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Carbohydrates:")
                    Text(text = String.format("%.2f", carboSum))
                }

                if (isExpanded.value) {
                    Spacer(modifier = Modifier.size(20.dp))
                    foodList.items.forEach {
                        Divider(modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.size(15.dp))
                        Text(text = it.name.capitalize(), fontSize = 25.sp)
                        Spacer(modifier = Modifier.size(15.dp))
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "kcal:")
                                Text(text = String.format("%.2f", it.calories))
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Fat")
                                Text(text = String.format("%.2f", it.fat_total_g))
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Protein")
                                Text(text = String.format("%.2f", it.protein_g))
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Carbohydrates:")
                                Text(text = String.format("%.2f", it.carbohydrates_total_g))
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Serving size")
                                Text(text = String.format("%.2f", it.serving_size_g))
                            }
                        }
                        Divider(modifier = Modifier.fillMaxWidth())
                    }
                }

            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListOfMealsPreview() {
    val food1 = Food(
        calories = 100.0,
        carbohydrates_total_g = 20.0,
        fat_total_g = 5.0,
        name = "Chicken Breast",
        protein_g = 25.0,
        serving_size_g = 100.0
    )

    val food2 = Food(
        calories = 50.0,
        carbohydrates_total_g = 10.0,
        fat_total_g = 2.5,
        name = "Brown Rice",
        protein_g = 2.5,
        serving_size_g = 50.0
    )

    val foodList = FoodList(
        name = "Healthy Meal",
        items = mutableListOf(food1, food2),
        date = Date()
    )
    ListOfMeals(foodList = foodList)
}
