package com.example.momonotes.screens.details

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.momonotes.model.Nota
import com.example.momonotes.navigation.Screens
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
            ) {
                navController.navigate(route = Screens.FormScreen.name + "/$idNota")
            }
        }
    ) { paddingValues ->
        Column {
            Column(
                modifier
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column {
                        Text(
                            state.nota.descricao,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            state.nota.data,
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.Start)
                        )
                    }
                }
            }
            LazyColumn() {
                itemsIndexed(state.nota.tarefas) { index, nota ->
                    val isChecked by remember {
                        mutableStateOf(state.nota.tarefasBoolean[index])
                    }
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        elevation = 10.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Checkbox(
                                checked = state.nota.tarefasBoolean[index],
                                onCheckedChange = { checked ->
                                    onEvent(DetailsEvent.OnChangeTarefa(index, checked))
                                },
                            )

                            Text(
                                text = nota,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.padding(horizontal = 8.dp)
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