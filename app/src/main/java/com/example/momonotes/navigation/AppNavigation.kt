package com.example.momonotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.momonotes.ui.form.FormScreen
import com.example.momonotes.ui.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.name
    ) {
        composable(Screens.HomeScreen.name) {
//            HomeScreen()
        }
        composable(Screens.FormScreen.name) {
//            FormScreen()
        }
    }
}