package com.example.momonotes.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.momonotes.model.Nota
import com.example.momonotes.screens.components.TopAppBarVoltar
import com.example.momonotes.ui.theme.MomoNotesTheme

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    idNota: String?,
    navController: NavController,
    state: DetailsState,
    onEvent: (DetailsEvent) -> Unit = {},
) {
    idNota?.let {
        onEvent(DetailsEvent.GetIdNota(it))
    }

    Scaffold(
        topBar = {
            TopAppBarVoltar(
                navController = navController,
                titulo = state.nota.titulo,
                isDetails = true,
                onEventDetails = onEvent
            )
        }
    ) { paddingValues ->
        Column(
            modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp),
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column {
                    Text(
                        state.nota.descricao,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        state.nota.descricao,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Start)
                    )
                }
            }

            LazyColumn() {
                itemsIndexed(state.nota.tarefas) { index, nota ->
                    Card(
                        modifier = Modifier.padding(8.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = 10.dp
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = nota, style = MaterialTheme.typography.body2)

                            Checkbox(
                                checked = state.nota.tarefasBoolean[index],
                                onCheckedChange = { onEvent(DetailsEvent.OnChangeTarefa(index)) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    MomoNotesTheme {
        DetailsScreen(
            idNota = "",
            navController = NavController(LocalContext.current),
            state = DetailsState(
                nota = Nota(
                    titulo = "titulo",
                    descricao = "O Crossfade é geralmente usado para envolver a parte do seu código onde você decide qual composição deve ser exibida com base em algum estado ou variável. No seu caso, o NavHost é a área onde você está trocando entre diferentes telas usando o composable para cada destino. Você pode aplicar o Crossfade ao redor do bloco NavHost, como ",
                    data = "24/11/1996",
                    tarefas = listOf("teste 1", "teste 2"),
                    tarefasBoolean = listOf(false, false)
                )
            ),
            onEvent = {}
        )
    }
}