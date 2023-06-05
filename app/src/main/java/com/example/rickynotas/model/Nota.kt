package com.example.rickynotas.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.*

data class Nota @RequiresApi(Build.VERSION_CODES.O) constructor(
    var id: String = UUID.randomUUID().toString(),
    var titulo: String = "",
    var descricao: String = "",
    var tarefas: MutableList<String> = mutableListOf(),
    var tarefasBool: MutableList<Boolean> = mutableListOf(),
    var data: LocalDateTime = LocalDateTime.now(),
    var finalizado:Boolean = false

)
