package com.example.calorieapp.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    drawerState: DrawerState, content: @Composable () -> Unit, navController: NavController
) {

    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(

            ) {
                Spacer(Modifier.height(12.dp))
                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home Screen"
                        )
                    },
                    label = {  },
                    selected = false,
                    onClick = { navController.navigate("HomeScreen") })
                Spacer(modifier = Modifier.size(8.dp))
                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Meal"
                        )
                    },
                    label = { Text(text = "Random") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    })
            }
            Spacer(modifier = Modifier.size(8.dp))
            NavigationDrawerItem(
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                ),
                icon = {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "List"
                    )
                },
                label = { Text(text = "Random") },
                selected = false,
                onClick = { navController.navigate("ListScreen") })

        },
        content = content

    )
}
