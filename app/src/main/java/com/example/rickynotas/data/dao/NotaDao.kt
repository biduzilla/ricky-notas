package com.example.rickynotas.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickynotas.model.Nota
import kotlinx.coroutines.flow.Flow


@Dao
interface NotaDao {

    @Query("SELECT * FROM Nota")
    fun buscaTodos(): Flow<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(vararg nota: Nota)

    @Delete
    suspend fun remove(nota: Nota)

    @Query("SELECT * FROM Nota WHERE id = :id")
    fun buscarPorId(id: String): Flow<Nota?>
}