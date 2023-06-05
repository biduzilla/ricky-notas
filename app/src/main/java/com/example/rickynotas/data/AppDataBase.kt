package com.example.rickynotas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickynotas.data.dao.NotaDao
import com.example.rickynotas.model.Nota

@Database(
    entities = [
        Nota::class,
    ],
    version = 1,
    exportSchema = true
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun notaDao(): NotaDao

    companion object {
        @Volatile
        private var db: AppDataBase? = null

        fun instancia(context: Context): AppDataBase {
            return db ?: Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "ricky_notas.db"
            )
                .build().also {
                    db = it
                }
        }
    }
}