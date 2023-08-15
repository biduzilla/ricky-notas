package com.example.momonotes.screens.details

import android.content.Context

sealed interface DetailsEvent {
    data class GetIdNota(val idNota: String) : DetailsEvent
    data class OnChangeTarefa(
        val index: Int,
        val checked: Boolean
    ) : DetailsEvent
}