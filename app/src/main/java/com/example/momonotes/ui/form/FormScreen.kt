package com.example.momonotes.ui.form

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.momonotes.ui.components.CardTarefa
import com.example.momonotes.ui.components.TextFieldCompleto
import com.example.momonotes.ui.components.TopAppBarVoltar
import com.example.momonotes.ui.theme.MomoNotesTheme

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FormScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    state: FormState,
    onEvent: (FormEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarVoltar(
                navController = navController,
                onEvent = onEvent,
                titulo = "Criar Nota"
            )
        }
    ) { paddingValues ->
        Column(
            modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier
                    .padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(16.dp))
                ErrorText(
                    isError = state.onErrorTitulo,
                )
                TextFieldCompleto(
                    isError = state.onErrorTitulo,
                    value = state.titulo,
                    onChange = { onEvent(FormEvent.SetTitulo(it)) },
                    label = "Título",
                )

                Spacer(modifier = Modifier.height(16.dp))
                ErrorText(
                    isError = state.onErrorDescricao,
                )
                TextFieldCompleto(
                    isError = state.onErrorDescricao,
                    value = state.descricao,
                    onChange = { onEvent(FormEvent.SetDescricao(it)) },
                    label = "Descrição",
                )

                Spacer(modifier = Modifier.height(16.dp))
                ErrorText(
                    isError = state.onErrorTarefa,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextFieldCompleto(
                        isError = state.onErrorTarefa,
                        value = state.tarefa,
                        onChange = { onEvent(FormEvent.SetTarefa(it)) },
                        label = "Tarefa",
                        modifier = Modifier
                            .weight(4f)
                    )

                    Button(
                        onClick = { onEvent(FormEvent.AddTarefa) },
                        Modifier
                            .padding(horizontal = 6.dp)
                            .padding(top = 8.dp)
                            .weight(1f)
                            .align(Alignment.CenterVertically)

                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    itemsIndexed(state.tarefas) { index, tarefa ->
                        CardTarefa(tarefa = tarefa, index = index, onEvent = onEvent)
                    }
                }

            }
        }
    }
}

@Composable
private fun ErrorText(
    modifier: Modifier = Modifier,
    isError: Boolean,
    mensagem: String = "Campo Obrigatório",
) {
    if (isError) {
        Text(text = mensagem, color = Color.Red, modifier = modifier)
    }
}

@Preview
@Composable
fun FormScreenPreview() {
    MomoNotesTheme {
        val state = FormState()
        val context = LocalContext.current
        FormScreen(state = state, onEvent = {}, navController = NavController(context))
    }
}