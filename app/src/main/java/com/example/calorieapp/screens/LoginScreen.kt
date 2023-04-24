package com.example.calorieapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.calorieapp.notifications.AddMealNotification

@Composable
fun LoginPage(navController: NavController) {
    var isMember by remember { mutableStateOf(true) }
    var addressEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else mutableStateOf(true)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasNotificationPermission = isGranted
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = addressEmail,
                onValueChange = {
                    addressEmail = it
                },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = "Email")},
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.moveFocus(FocusDirection.Down)
                })

            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = "Password")}
            )
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
                if(hasNotificationPermission) {
                AddMealNotification(context).showNotification()
            }
            }) {
                if (isMember){
                    Text(text = "Login")
                }else{
                    Text(text = "Sign in")
                }
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedButton(onClick = {navController.navigate("HomeScreen")}) {
                Text(text = "Use only on this device")
            }
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedButton(onClick = {
                isMember = !isMember
            }) {
                if (isMember){
                    Text(text = "You don't have an account? Sign in")
                }else{
                    Text(text = "You have an account? Login")
                }

            }
        }
    }
}