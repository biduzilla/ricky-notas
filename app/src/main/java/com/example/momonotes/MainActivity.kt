package com.example.momonotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.momonotes.navigation.AppNavigation
import com.example.momonotes.ui.theme.MomoNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MomoNotesTheme {
                AppNavigation(this)
            }
        }
    }
}

