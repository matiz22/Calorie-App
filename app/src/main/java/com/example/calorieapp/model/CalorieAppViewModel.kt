package com.example.calorieapp.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorieapp.repository.DatabaseRepository
import com.example.calorieapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class CalorieAppViewModel @Inject constructor(
    private val repository: FoodRepository,
    private val databaseRepository: DatabaseRepository
) :
    ViewModel() {
    private var _RemindersList = MutableStateFlow<List<ReminderItem>>(emptyList())
    var remindersList = _RemindersList.asStateFlow()


    private val _FoodList = MutableStateFlow<List<FoodList>>(emptyList())
    val foodList = _FoodList.asStateFlow()

    private var _isSearching = MutableStateFlow(false)
    var isSearching = _isSearching.asStateFlow()

    private var _queryResult = MutableStateFlow(
        FoodList(
            items = mutableListOf()
        )
    )
    var queryResult = _queryResult.asStateFlow()
    fun getReminders() {
        viewModelScope.launch {
            databaseRepository.getReminders().collect {
                _RemindersList.value = it
            }
        }
    }

    fun saveReminder(reminderItem: ReminderItem) {
        viewModelScope.launch {
            databaseRepository.insertReminder(reminderItem = reminderItem)
        }
    }

    fun getSearchedFood(searchText: String) {
        viewModelScope.launch {

            if (searchText.replace(" ", "") != "") {
                _isSearching.value = true
                try {
                    val result = repository.getAllSearchedFood(searchText)
                    val currentItems = _queryResult.value.items
                    _queryResult.value =
                        FoodList(items = (currentItems + result.items).toMutableList())
                    if (!result.items.isEmpty()) {
                        _isSearching.value = false
                    }
                } catch (e: Exception) {
                    Log.e("API Error", e.toString())
                    _isSearching.value = false
                }
            }

        }
    }

    fun deleteFood(food: Food) {
        viewModelScope.launch {
            val currentList = _queryResult.value.items.toMutableList()
            currentList.remove(food)
            val newList = FoodList(items = currentList)
            _queryResult.value = newList
        }
    }

    fun editPortion(food: Food, newGram: Double) {
        val percentage = newGram / food.serving_size_g
        food.serving_size_g = newGram
        food.calories *= percentage
        food.fat_total_g *= percentage
        food.carbohydrates_total_g *= percentage
        food.protein_g *= percentage

    }

    fun addFood(food: Food) {
        viewModelScope.launch {
            _queryResult.value.items.add(food)
        }
    }

    fun addFoodList(value: String) {
        _queryResult.value.name = value
        _queryResult.value.date = Date()
        viewModelScope.launch {
            databaseRepository.insertFoodList(_queryResult.value)
            _queryResult.value =
                FoodList(
                    items = mutableListOf()
            )
        }

    }

    fun getFoodList() {
        viewModelScope.launch {
            databaseRepository.getFoodList().collect {
                _FoodList.value = it
            }
        }
    }

    fun deleteReminder(reminderItem: ReminderItem) {
        viewModelScope.launch {
            databaseRepository.deleteReminder(reminderItem)
        }
        getReminders()
    }


}