package com.example.momonotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.momonotes.ui.form.FormScreen
import com.example.momonotes.ui.form.FormViewModel
import com.example.momonotes.ui.theme.MomoNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MomoNotesTheme {
                val viewModel by viewModels<FormViewModel>()
                val state by viewModel.state.collectAsState()

                FormScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}

