package com.example.rickynotas.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
data class Nota(
    @PrimaryKey(autoGenerate = false)
    var id: String = UUID.randomUUID().toString(),
    var titulo: String = "",
    var descricao: String = "",
    var tarefas: MutableList<String> = mutableListOf(),
    var tarefasBool: MutableList<Boolean> = mutableListOf(),
    var data: String = "",
    var finalizado: Boolean = false

) : Parcelable
