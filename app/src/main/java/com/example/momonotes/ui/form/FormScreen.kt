package com.example.momonotes.ui.form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.momonotes.ui.components.TextFieldCompleto
import com.example.momonotes.ui.components.TopAppBarVoltar
import com.example.momonotes.ui.theme.MomoNotesTheme

@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    state: FormStateUi,
    onClickVoltar: () -> Unit = {},
    onClickDone: () -> Unit = {},
) {
    Scaffold(
        topBar = { TopAppBarVoltar(onClickVoltar, onClickDone, titulo = "Criar Nota") }
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
                    onChange = state.onChangeTitulo,
                    label = "Tarefa",
                )

                Spacer(modifier = Modifier.height(16.dp))
                ErrorText(
                    isError = state.onErrorDescricao,
                )
                TextFieldCompleto(
                    isError = state.onErrorDescricao,
                    value = state.descricao,
                    onChange = state.onChangeDescricao,
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
                        onChange = state.onChangeTarefa,
                        label = "Tarefa",
                        modifier = Modifier
                            .weight(4f)
                    )

                    Button(
                        onClick = { state.tarefas.add(state.tarefa) },
                        Modifier
                            .padding(horizontal = 8.dp)
                            .weight(1f)
                            .align(Alignment.CenterVertically)

                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(listOf("Tarefa", "Tarefa", "Tarefa", "Tarefa")) { tarefa ->
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
                                    onClick = { state.tarefas.add(state.tarefa) },
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
        FormScreen(state = FormStateUi())
    }
}