package com.example.momonotes.ui.form

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momonotes.extension.convertToString
import com.example.momonotes.model.Nota
import com.example.momonotes.repository.NotaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val repository: NotaRepository) : ViewModel() {

    private val _state = MutableStateFlow(FormState())
    val state = _state.asStateFlow()

    fun onEvent(event: FormEvent) {
        when (event) {
            FormEvent.AddTarefa -> {
                val tarefa = _state.value.tarefa
                val tarefas: MutableList<String> = _state.value.tarefas.toMutableList()

                tarefas.add(tarefa)

                _state.update {
                    it.copy(
                        tarefas = tarefas.toList(),
                        tarefasBoolean = List(tarefas.size) { false },
                        tarefa = ""
                    )
                }
            }

            is FormEvent.SetDescricao -> {
                _state.update {
                    it.copy(
                        descricao = event.descricao,
                        onErrorDescricao = false
                    )
                }
            }

            is FormEvent.SetTarefa -> {
                _state.update {
                    it.copy(
                        tarefa = event.tarefa,
                        onErrorTarefa = false
                    )
                }
            }

            is FormEvent.SetTitulo -> {
                _state.update {
                    it.copy(
                        titulo = event.titulo,
                        onErrorTitulo = false
                    )
                }
            }

            is FormEvent.RemoveTarefa -> {
                val tarefas: MutableList<String> = _state.value.tarefas.toMutableList()
                tarefas.removeAt(event.tarefaIndex)
                _state.update {
                    it.copy(
                        tarefas = tarefas.toList(),
                        tarefasBoolean = List(tarefas.size) { false }
                    )
                }
            }

            is FormEvent.OnClickSalvarNota -> {
                val titulo: String = _state.value.titulo
                val descricao: String = _state.value.descricao
                val tarefas: List<String> = _state.value.tarefas

                if (titulo.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorTitulo = true
                        )
                    }
                    return
                } else {
                    _state.update {
                        it.copy(
                            onErrorTitulo = false
                        )
                    }
                }

                if (descricao.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorDescricao = true
                        )
                    }
                    return
                } else {
                    _state.update {
                        it.copy(
                            onErrorDescricao = false
                        )
                    }
                }

                if (tarefas.isEmpty() && _state.value.tarefa.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorTarefa = true
                        )
                    }
                    return
                } else {
                    _state.update {
                        it.copy(
                            onErrorTarefa = false
                        )
                    }
                }

                _state.update {
                    it.copy(
                        tarefa = "",
                        titulo = "",
                        descricao = ""
                    )
                }

                with(_state.value) {
                    val nota = Nota(
                        titulo = titulo,
                        descricao = descricao,
                        data = Date.from(Instant.now()).convertToString(),
                        tarefas = tarefas,
                        tarefasBoolean = tarefasBoolean
                    )

                    viewModelScope.launch {
                        repository.addNota(nota)
                    }
                }
            }
        }
    }
}