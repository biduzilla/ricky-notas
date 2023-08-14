package com.example.momonotes.ui.form

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
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
                        tarefasBoolean = List(tarefas.size) { false }
                    )
                }
            }

            FormEvent.SaveNote -> {
                val titulo: String = _state.value.titulo
                val descricao: String = _state.value.descricao
                val tarefas: List<String> = _state.value.tarefas
                val tarefasBoolean: List<Boolean> = _state.value.tarefasBoolean

                if (titulo.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorTitulo = true
                        )
                    }
                    return
                }

                if (descricao.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorDescricao = true
                        )
                    }
                    return
                }

                if (_state.value.tarefas.isEmpty()) {
                    _state.update {
                        it.copy(
                            onErrorTarefa = true
                        )
                    }
                    return
                }

                val nota = Nota(
                    titulo = titulo,
                    descricao = descricao,
                    tarefas = tarefas,
                    tarefasBoolean = tarefasBoolean
                )

                viewModelScope.launch {
                    repository.addNota(nota)
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

            is FormEvent.OnClickDone -> {
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

                Toast.makeText(event.context, "Done", Toast.LENGTH_SHORT).show()
            }

            is FormEvent.OnClickVoltar -> {
                Toast.makeText(event.context, "Voltar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}