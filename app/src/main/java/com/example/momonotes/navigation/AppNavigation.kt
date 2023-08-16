package com.example.momonotes.navigation

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.momonotes.screens.details.DetailsScreen
import com.example.momonotes.screens.details.DetailsViewModel
import com.example.momonotes.screens.form.FormScreen
import com.example.momonotes.screens.form.FormViewModel
import com.example.momonotes.screens.home.HomeScreen
import com.example.momonotes.screens.home.HomeViewModel

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
        composable(
            Screens.FormScreen.name + "/idNota}",
            arguments = listOf(navArgument(name = "idNota") {
                type = NavType.StringType
            })
        ) { backStrack ->
            val viewModel by activity.viewModels<FormViewModel>()
            val state by viewModel.state.collectAsState()

            FormScreen(
                idNota = backStrack.arguments?.getString("idNota"),
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composable(
            Screens.DetailsScreen.name + "/{idNota}",
            arguments = listOf(navArgument(name = "idNota") { type = NavType.StringType })
        ) { backStackEntry ->

            val viewModel by activity.viewModels<DetailsViewModel>()
            val state by viewModel.state.collectAsState()

            DetailsScreen(
                navController = navController,
                idNota = backStackEntry.arguments?.getString("idNota"),
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }

}