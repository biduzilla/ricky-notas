package com.example.momonotes.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.momonotes.ui.form.FormEvent

@Composable
fun TopAppBarVoltar(onEvent: (FormEvent) -> Unit = {}, titulo: String) {
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
                        onClick = { onEvent(FormEvent.OnClickVoltar(context)) },
                        enabled = true,
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }

                    IconButton(
                        onClick = { onEvent(FormEvent.OnClickDone(context)) },
                        enabled = true,
                    ) {
                        Icon(imageVector = Icons.Default.Done, contentDescription = null)
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
    TopAppBarVoltar({}, "Titulo")
}