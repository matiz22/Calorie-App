package com.example.calorieapp.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.calorieapp.components.ReminderWidget
import com.example.calorieapp.model.CalorieAppViewModel
import com.example.calorieapp.model.ReminderItem
import com.example.calorieapp.notifications.NotificationScheduler
import com.example.calorieapp.repository.DatabaseRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(navController: NavHostController, viewModel: CalorieAppViewModel) {
    val context = LocalContext.current
    viewModel.getReminders()
    val reminders = viewModel.remindersList.collectAsState().value
    val state = rememberTimePickerState()
    val showingPicker = remember { mutableStateOf(false) }


    if (showingPicker.value) {
        AlertDialog(onDismissRequest = {
            showingPicker.value = false
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
                    TimePicker(state = state)
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedButton(onClick = {
                        val reminder =  ReminderItem(id = "${state.hour}:${if (state.minute < 10) "0${state.minute}" else "${state.minute}"}")
                        viewModel.saveReminder(
                           reminder
                        )
                        NotificationScheduler(context = context).schedule(reminder)
                        viewModel.getReminders()
                        showingPicker.value = false
                    }) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Reminders") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showingPicker.value = !showingPicker.value }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },

        )
    {
        Column(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState()),
        ) {
            reminders.forEach {
                Box(modifier = Modifier.padding(10.dp)) {
                    ReminderWidget(reminderItem = it) {
                        viewModel.deleteReminder(reminderItem = it)
                        viewModel.getReminders()

                    }
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RemindersScreenPreview() {
    RemindersScreen(rememberNavController(), viewModel<CalorieAppViewModel>())

}