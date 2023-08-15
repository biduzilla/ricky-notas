package com.example.momonotes.screens.details

import android.content.Context

sealed interface DetailsEvent {
    object EditarNota : DetailsEvent
    data class onChangeTarefa(val index:Int):DetailsEvent
}