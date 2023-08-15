package com.example.momonotes.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momonotes.model.Nota
import com.example.momonotes.repository.NotaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NotaRepository) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private var notas: Flow<List<Nota>> = emptyFlow()

    init {
        viewModelScope.launch {
            notas = repository.getAllNotas()
        }

        viewModelScope.launch {
            notas.collect {
                _state.update { currentState ->
                    currentState.copy(
                        notas = it
                    )
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SetSearch -> {
                _state.update {
                    it.copy(
                        search = event.search
                    )
                }
                viewModelScope.launch {
                    notas.collect {
                        it.forEach { nota ->
                            val notasFiltradas = mutableListOf<Nota>()
                            if (nota.titulo.contains(event.search)) {
                                notasFiltradas.add(nota)
                            }
                            _state.update { currentState ->
                                currentState.copy(notasFiltradas = notasFiltradas.toList())
                            }
                        }
                    }
                }
            }
        }
    }
}