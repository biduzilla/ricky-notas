package com.example.momonotes.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.momonotes.screens.form.FormEvent

@Composable
fun CardTarefa(tarefa: String, index: Int, onEvent: (FormEvent) -> Unit = {}) {
    Card(
        modifier = Modifier.padding(top = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp

    ) {
        Row(
            Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tarefa,
                Modifier
                    .weight(4f)
                    .fillMaxWidth()
            )
            Button(
                onClick = { onEvent(FormEvent.RemoveTarefa(tarefaIndex = index)) },
                Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically)

            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    }

}