package com.example.momonotes.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momonotes.model.Nota
import com.example.momonotes.repository.NotaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val repository: NotaRepository) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.GetIdNota -> {
                viewModelScope.launch {
                    repository.getNoteById(event.idNota).let { nota ->
                        _state.update {
                            it.copy(
                                nota = nota
                            )
                        }
                    }
                }
            }

            is DetailsEvent.OnChangeTarefa -> {
                val nota: Nota = _state.value.nota
                val tarefas: MutableList<Boolean> = nota.tarefasBoolean.toMutableList()

                tarefas[event.index] = !tarefas[event.index]
                nota.tarefasBoolean = tarefas.toList()

                _state.update {
                    it.copy(
                        nota = nota
                    )
                }
            }
        }
    }
}