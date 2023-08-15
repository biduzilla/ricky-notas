package com.example.momonotes.repository

import com.example.momonotes.data.dao.NotaDatabaseDao
import com.example.momonotes.model.Nota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class NotaRepository @Inject constructor(private val notaDao: NotaDatabaseDao) {
    suspend fun addNota(nota: Nota) = notaDao.insertNota(nota)
    suspend fun updateNota(nota: Nota) = notaDao.updateNota(nota)
    suspend fun deleteNota(nota: Nota) = notaDao.deleteNota(nota)
    suspend fun deleteAll() = notaDao.deleteAll()
    fun getAllNotas(): Flow<List<Nota>> = notaDao.getAll().flowOn(Dispatchers.IO).conflate()
    suspend fun getNoteById(id:String):Nota = notaDao.getNoteById(id)
}