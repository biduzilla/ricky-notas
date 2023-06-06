package com.example.rickynotas.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromBooleanList(value: List<Boolean>?): String? {
        return value?.joinToString(separator = "&") { it.toString() }
    }

    @TypeConverter
    fun toBooleanList(value: String?): List<Boolean>? {
        return value?.split("&")?.map { it.toBoolean() }
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString(separator = "&")
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.split("&")?.map { it.trim() }
    }
}