package com.example.rickynotas.ui.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickynotas.databinding.ActivityFormBinding
import com.example.rickynotas.extension.Toast
import com.example.rickynotas.model.Nota
import com.example.rickynotas.ui.adapter.TarefasAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class FormActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        TarefasAdapter()
    }
    private var count: Int = 0

    private var note = Nota()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbarVoltar.txtTitulo.text = "Criar Nota"
        configRv()
        configClicks()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun configClicks() {
        with(binding) {
            btnAddTarefa.setOnClickListener {
                ocultarTeclado()

                val tarefa: String = edtTarefa.text.toString().trim()
                when {
                    tarefa.isEmpty() -> {
                        edtTarefa.requestFocus()
                        edtTarefa.error = "Campo Obrigatório"
                    }
                    else -> {
                        count++
                        note.tarefas.add(tarefa)
                        note.tarefasBool.add(false)
                        adapter.atualiza(note.tarefas)
                    }
                }
            }
            btnSalvar.setOnClickListener {
                ocultarTeclado()
                verificaCampos()
            }
        }
    }

    private fun configRv() {
        with(binding) {
            rvTarefas.adapter = adapter
            rvTarefas.layoutManager = LinearLayoutManager(baseContext)
            adapter.onClick = {
                count--
                note.tarefas.removeAt(it)
                note.tarefasBool.removeAt(it)
                adapter.atualiza(note.tarefas)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun verificaCampos() {
        with(binding) {
            val titulo: String = edtTitulo.text.toString().trim()
            val descricao: String = edtDescricao.text.toString().trim()
            when {
                titulo.isEmpty() -> {
                    edtTitulo.requestFocus()
                    edtTitulo.error = "Campo Obrigatório"
                }
                descricao.isEmpty() -> {
                    edtDescricao.requestFocus()
                    edtDescricao.error = "Campo Obrigatório"
                }
                else -> {
                    note.titulo = titulo
                    note.descricao = descricao

                    val dataStr = LocalDateTimeToString()

                    note.data = dataStr
                    Toast(baseContext, note.toString())
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun LocalDateTimeToString(): String {
        val data = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dataStr = data.format(formatter)
        return dataStr
    }

    private fun ocultarTeclado() {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.edtTitulo.windowToken, 0)
    }
}