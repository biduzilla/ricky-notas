package com.example.momonotes.ui.home

sealed interface HomeEvent {
    data class SetSearch(val search: String) : HomeEvent
    object GoToFormScreen : HomeEvent
}