package com.example.momonotes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "nota_tab")
data class Nota(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "nota_titulo")
    var titulo: String = "",

    @ColumnInfo(name = "nota_descricao")
    var descricao: String = "",

    @ColumnInfo(name = "nota_data")
    var data: String = "",

    @ColumnInfo(name = "nota_tarefas")
    var tarefas: List<String> = emptyList(),

    @ColumnInfo(name = "nota_tarefasBoolean")
    var tarefasBoolean: List<Boolean> = emptyList()
)
