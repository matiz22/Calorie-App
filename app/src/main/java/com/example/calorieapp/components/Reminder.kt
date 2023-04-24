package com.example.calorieapp.components

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorieapp.model.ReminderItem

@Composable
fun ReminderWidget(reminderItem: ReminderItem, onDelete: () -> Unit) {
    OutlinedCard(modifier = Modifier.fillMaxWidth(), ) {
        Row(
            modifier = Modifier
                .fillMaxWidth().height(70.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center) {
                Text(text = reminderItem.id, fontSize = 20.sp)
            }
            IconButton(onClick =onDelete) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "" )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    ReminderWidget(ReminderItem(id = "14:00"), {})
}