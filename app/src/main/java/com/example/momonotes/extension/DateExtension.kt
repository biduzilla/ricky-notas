package com.example.momonotes.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.convertToString(): String {
    return SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.getDefault()
    ).format(this)
}