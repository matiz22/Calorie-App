package com.example.calorieapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavController
import com.example.calorieapp.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController, openDrawer: DrawerState) {
    val scope = rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    var expanededMenu by remember {
        mutableStateOf(false)
    }
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        openDrawer.open()
                    }
                }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Text(text = context.getString(R.string.app_name))
        },
        actions = {
            IconButton(onClick = { expanededMenu = !expanededMenu }) {
                Icon(Icons.Default.MoreVert, "", tint = MaterialTheme.colorScheme.onPrimary)
            }
            DropdownMenu(expanded = expanededMenu, onDismissRequest = { expanededMenu = false }) {
                DropdownMenuItem(text = {
                    Text(text = "Account")
                },
                    onClick = { /*TODO*/ })
                DropdownMenuItem(text = {
                    Text(text = "Settings")
                },
                    onClick = { navController.navigate(route = "SettingsScreen") })
                DropdownMenuItem(text = {
                    Text(text = "Help")
                },
                    onClick = {
                        uriHandler.openUri("https://github.com/matiz22/CalorieApp")
                    })
            }
        })
}