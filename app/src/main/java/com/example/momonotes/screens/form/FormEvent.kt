package com.example.momonotes.screens.form

import android.content.Context

sealed interface FormEvent {
    object AddTarefa : FormEvent
    data class OnClickSalvarNota(val context: Context) : FormEvent
    data class RemoveTarefa(val tarefaIndex: Int) : FormEvent
    data class SetTitulo(val titulo: String) : FormEvent
    data class SetDescricao(val descricao: String) : FormEvent
    data class SetTarefa(val tarefa: String) : FormEvent


}