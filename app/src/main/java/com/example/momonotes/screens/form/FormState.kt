package com.example.momonotes.screens.form

data class FormState(
    val titulo: String = "",
    val descricao: String = "",
    val tarefa: String = "",
    val tarefas: List<String> = emptyList(),
    val tarefasBoolean: List<Boolean> = emptyList(),
    var onErrorTitulo: Boolean = false,
    var onErrorDescricao: Boolean = false,
    var onErrorTarefa: Boolean = false,
)