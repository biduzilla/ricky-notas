package com.example.momonotes.screens.home

import com.example.momonotes.model.Nota

data class HomeState(
    val search: String = "",
    val notas: List<Nota> = emptyList(),
    val notasFiltradas: List<Nota> = emptyList()
)