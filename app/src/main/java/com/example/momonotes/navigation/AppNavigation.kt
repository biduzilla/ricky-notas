package com.example.momonotes.navigation

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.momonotes.ui.form.FormScreen
import com.example.momonotes.ui.form.FormViewModel
import com.example.momonotes.ui.home.HomeScreen
import com.example.momonotes.ui.home.HomeState
import com.example.momonotes.ui.home.HomeViewModel

@Composable
fun AppNavigation(activity: ComponentActivity) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.name
    ) {
        composable(Screens.HomeScreen.name) {
            val viewModel by activity.viewModels<HomeViewModel>()
            val state by viewModel.state.collectAsState()

            HomeScreen(navController = navController, state = state, onEvent = viewModel::onEvent)
        }
        composable(Screens.FormScreen.name) {
            val viewModel by activity.viewModels<FormViewModel>()
            val state by viewModel.state.collectAsState()

            FormScreen(navController = navController, state = state, onEvent = viewModel::onEvent)
        }
    }

}