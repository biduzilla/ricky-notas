package com.example.rickynotas.ui.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickynotas.CHAVE_NOTA_ID
import com.example.rickynotas.data.AppDatabase
import com.example.rickynotas.data.dao.NotaDao
import com.example.rickynotas.databinding.ActivityFormBinding
import com.example.rickynotas.model.Nota
import com.example.rickynotas.ui.adapter.TarefasAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FormActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        TarefasAdapter()
    }

    private val notaDao: NotaDao by lazy {
        val db = AppDatabase.instancia(this)
        db.notaDao()
    }
    private var count: Int = 0
    private var note = Nota()
    private var notaId: String? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbarVoltar.txtTitulo.text = "Criar Nota"
        notaId = intent.getStringExtra(CHAVE_NOTA_ID)
        notaId?.let {
            CoroutineScope(Dispatchers.IO).launch {
                recuperaNota()
            }
        }
        configRv()
        configClicks()
    }

    override fun onResume() {
        super.onResume()
        notaId?.let {
            configDados()
        }

    }

    private suspend fun recuperaNota() {
        val notaId = intent.getStringExtra(CHAVE_NOTA_ID)

        notaId?.let { id ->
            notaDao.buscarPorId(id).collect {
                it?.let { notaRecuperada ->
                    note = notaRecuperada

                }
            }
        }
    }

    private fun configDados() {
        with(binding) {
            edtTitulo.setText(note.titulo)
            edtDescricao.setText(note.descricao)
            adapter.atualiza(note.tarefas)
            toolbarVoltar.txtTitulo.text = "Atualizar Tarefa"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun configClicks() {
        with(binding) {
            toolbarVoltar.btnMenu.visibility = View.GONE
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
                        edtTarefa.setText("")
                    }
                }
            }
            btnSalvar.setOnClickListener {
                ocultarTeclado()
                verificaCampos()
            }
            toolbarVoltar.btnVoltar.setOnClickListener {
                finish()
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

                    CoroutineScope(Dispatchers.IO).launch { notaDao.salvar(note) }

                    finish()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun LocalDateTimeToString(): String {
        val data = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return data.format(formatter)
    }

    private fun ocultarTeclado() {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.edtTitulo.windowToken, 0)
    }
}