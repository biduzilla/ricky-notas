package com.example.momonotes.screens.home

sealed interface HomeEvent {
    data class SetSearch(val search: String) : HomeEvent
}