package com.example.rickynotas.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
data class Tarefa(
    var index: Int = 0,
    var tarefa: String = "",
    var checkBox: Boolean = false
) : Parcelable
