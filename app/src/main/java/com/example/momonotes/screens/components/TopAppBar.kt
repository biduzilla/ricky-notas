package com.example.momonotes.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.momonotes.navigation.Screens
import com.example.momonotes.screens.details.DetailsEvent
import com.example.momonotes.screens.form.FormEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopAppBarVoltar(
    navController: NavController,
    onEventForm: (FormEvent) -> Unit = {},
    onEventDetails: (DetailsEvent) -> Unit = {},
    titulo: String,
    isDetails: Boolean = false
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    TopAppBar {
        Box(Modifier.height(32.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        enabled = true,
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }

                    IconButton(
                        onClick = {
                            if (isDetails) {
                                navController.navigate(Screens.FormScreen.name)
                            } else {
                                onEventForm(FormEvent.OnClickSalvarNota(context))
                            }

                            keyboardController?.hide()
                            navController.popBackStack()
                        },
                        enabled = true,
                    ) {
                        Icon(
                            imageVector = if (isDetails) Icons.Default.Edit else Icons.Default.Done,
                            contentDescription = null
                        )
                    }
                }
            }
            //Title
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                ProvideTextStyle(value = MaterialTheme.typography.h6) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            text = titulo
                        )
                    }
                }
            }

        }

    }
}

@Preview
@Composable
fun TopAppBarVoltarPreview() {
    val context = LocalContext.current
    TopAppBarVoltar(NavController(context), {}, {}, "Titulo")
}