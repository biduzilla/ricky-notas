package com.example.momonotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.momonotes.data.converter.Converters
import com.example.momonotes.data.dao.NotaDatabaseDao
import com.example.momonotes.model.Nota

@Database(
    entities = [
        Nota::class
    ], version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NotaDatabase:RoomDatabase() {
    abstract fun notaDao(): NotaDatabaseDao
}