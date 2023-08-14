package com.example.momonotes.ui.form

import android.content.Context

sealed interface FormEvent {
    object SaveNote : FormEvent
    object AddTarefa : FormEvent
    data class OnClickVoltar(val context: Context) : FormEvent
    data class OnClickDone(val context: Context) : FormEvent
    data class RemoveTarefa(val tarefaIndex: Int) : FormEvent
    data class SetTitulo(val titulo: String) : FormEvent
    data class SetDescricao(val descricao: String) : FormEvent
    data class SetTarefa(val tarefa: String) : FormEvent


}