package com.example.momonotes.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.momonotes.model.Nota
import com.example.momonotes.navigation.Screens
import com.example.momonotes.ui.components.TextFieldCompleto
import com.example.momonotes.ui.theme.MomoNotesTheme
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavController,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(route = Screens.FormScreen.name)
        }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }) { paddingValues ->
        Column {
            Surface(
                color = MaterialTheme.colors.primary,
            ) {
                TextFieldCompleto(
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 16.dp
                        ),
                    isError = false,
                    value = state.search,
                    onChange = {
                        onEvent(HomeEvent.SetSearch(it))
                    },
                    label = "Search",
                    icon = Icons.Default.Search,
                    imeAction = ImeAction.Done
                )
            }

            LazyColumn(contentPadding = paddingValues) {
                items(state.notas) { nota ->
                    CardNota(nota = nota, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun CardNota(nota: Nota, modifier: Modifier = Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier,
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = nota.titulo,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                if (expanded) {
                    Text(
                        text = nota.descricao,
                        style = MaterialTheme.typography.body2
                    )
                }
                Text(
                    text = nota.data, style = MaterialTheme.typography.h6
                )
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun CardNotaPreview() {
    MomoNotesTheme {
        CardNota(
            nota = Nota(
                titulo = "Titulo",
                data = "24/11/1996"
            )
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val context = LocalContext.current
    MomoNotesTheme {
        HomeScreen(NavController(context),HomeState(), {})
    }
}