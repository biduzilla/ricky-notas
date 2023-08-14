package com.example.momonotes.ui.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momonotes.extension.convertToString
import com.example.momonotes.model.Nota
import com.example.momonotes.repository.NotaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val repository: NotaRepository) : ViewModel() {

    private val _state = MutableStateFlow(FormState())

    fun onEvent(event:FormEvent){
        when(event){
            FormEvent.AddTarefa -> TODO()
            FormEvent.OnErrorTitulo -> TODO()
            FormEvent.SaveNote -> TODO()
            is FormEvent.SetDescricao -> TODO()
            is FormEvent.SetTarefa -> TODO()
            is FormEvent.SetTitulo -> TODO()
            FormEvent.onErrorDescricao -> TODO()
            FormEvent.onErrorTarefa -> TODO()
        }
    }
}