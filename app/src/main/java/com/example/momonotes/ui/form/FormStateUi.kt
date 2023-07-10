package com.example.momonotes.ui.form

data class FormStateUi(
    val titulo: String = "",
    val descricao: String = "",
    val tarefa: String = "",
    val tarefas: MutableList<String> = mutableListOf(),
    val onChangeTitulo: (String) -> Unit = {},
    val onChangeDescricao: (String) -> Unit = {},
    val onChangeTarefa: (String) -> Unit = {},
    val onErrorTitulo: Boolean = false,
    val onErrorDescricao: Boolean = false,
    val onErrorTarefa: Boolean = false,
    )