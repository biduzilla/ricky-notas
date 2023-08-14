package com.example.momonotes.ui.form

sealed interface FormEvent {
    object SaveNote : FormEvent
    object AddTarefa : FormEvent
    data class SetTitulo(val titulo: String) : FormEvent
    data class SetDescricao(val descricao: String) : FormEvent
    data class SetTarefa(val tarefa: String) : FormEvent
    object OnErrorTitulo : FormEvent
    object onErrorDescricao : FormEvent
    object onErrorTarefa : FormEvent

}