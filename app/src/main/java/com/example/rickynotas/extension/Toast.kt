package com.example.rickynotas.extension

import android.content.Context
import android.widget.Toast

fun Toast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}