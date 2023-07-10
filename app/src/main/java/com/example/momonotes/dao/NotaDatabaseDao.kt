package com.example.momonotes.dao

import androidx.room.*
import com.example.momonotes.model.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDatabaseDao {

    @Query("SELECT * from nota_tab")
    fun getAll(): Flow<List<Nota>>

    @Query("SELECT * from nota_tab where id=:id")
    suspend fun getNoteById(id: String): Nota

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNota(nota: Nota)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNota(nota: Nota)

    @Query("DELETE from nota_tab")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNota(nota: Nota)
}