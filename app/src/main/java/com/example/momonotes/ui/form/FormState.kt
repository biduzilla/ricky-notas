package com.example.momonotes.ui.form

data class FormState(
    val titulo: String = "",
    val descricao: String = "",
    val tarefa: String = "",
    val tarefas: List<String> = emptyList(),
    val tarefasBoolean: List<Boolean> = emptyList(),
    val onErrorTitulo: Boolean = false,
    val onErrorDescricao: Boolean = false,
    val onErrorTarefa: Boolean = false,
)