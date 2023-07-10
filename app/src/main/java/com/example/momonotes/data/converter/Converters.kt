package com.example.momonotes.data.converter

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromBooleanList(value: List<Boolean>?): String? {
        return value?.joinToString(separator = "&") {
            it.toString()
        }
    }

    @TypeConverter
    fun toBooleanList(value: String?): List<Boolean>? {
        return value?.split("&")?.map {
            it.toBoolean()
        }
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString("&")
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.split("&")?.map { it.trim() }
    }
}