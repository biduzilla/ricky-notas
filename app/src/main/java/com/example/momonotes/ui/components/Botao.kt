package com.example.momonotes.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Botao(
    modifier: Modifier = Modifier,
    texto: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier

    ) {
        Text(text = texto)
    }
}