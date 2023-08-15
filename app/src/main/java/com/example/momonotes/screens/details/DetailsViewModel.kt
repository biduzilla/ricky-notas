package com.example.momonotes.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momonotes.model.Nota
import com.example.momonotes.repository.NotaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: NotaRepository) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.GetIdNota -> {
                if (_state.value.nota.titulo.isBlank()) {
                    viewModelScope.launch {
                        val nota = repository.getNoteById(event.idNota)
                        _state.update { currentState ->
                            currentState.copy(nota = nota)
                        }
                    }
                }
            }

            is DetailsEvent.OnChangeTarefa -> {
                _state.update { currentState ->
                    val updatedTarefas = currentState.nota.tarefasBoolean.toMutableList()
                    updatedTarefas[event.index] = event.checked
                    currentState.copy(nota = currentState.nota.copy(tarefasBoolean = updatedTarefas.toList()))
                }
                viewModelScope.launch {
                    repository.updateNota(_state.value.nota)
                }
            }
        }
    }
}