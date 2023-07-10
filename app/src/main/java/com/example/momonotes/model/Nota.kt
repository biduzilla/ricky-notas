package com.example.momonotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
class Nota(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var titulo: String,
    var descricao: String,
    var data: String,
    var tarefas: List<String> = emptyList(),
    var tarefasBoolean: List<Boolean> = emptyList()
)
