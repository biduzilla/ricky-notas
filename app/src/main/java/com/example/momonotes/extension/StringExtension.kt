package com.example.momonotes.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.convertToDate(): Date? {
    return SimpleDateFormat(
        "d/M/yyyy",
        Locale.getDefault()
    ).parse(this)
}