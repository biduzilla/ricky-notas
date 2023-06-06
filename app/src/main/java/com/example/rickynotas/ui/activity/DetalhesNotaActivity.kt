package com.example.rickynotas.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickynotas.CHAVE_NOTA_ID
import com.example.rickynotas.R
import com.example.rickynotas.data.AppDatabase
import com.example.rickynotas.data.dao.NotaDao
import com.example.rickynotas.databinding.ActivityDetalhesNotaBinding
import com.example.rickynotas.databinding.DialogExcluirBinding
import com.example.rickynotas.databinding.DialogFinalizarBinding
import com.example.rickynotas.extension.Toast
import com.example.rickynotas.model.Nota
import com.example.rickynotas.model.Tarefa
import com.example.rickynotas.ui.adapter.TarefasCheckboxAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetalhesNotaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesNotaBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        TarefasCheckboxAdapter()
    }
    private val notaDao: NotaDao by lazy {
        val db = AppDatabase.instancia(baseContext)
        db.notaDao()
    }

    private var nota = Nota()
    private val tarefas: MutableList<Tarefa> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            recuperaNota()
        }
        configMenu()
        configClick()
    }

    private fun configClick() {
        with(binding) {
            toolbarVoltar.btnVoltar.setOnClickListener {
                finish()
            }
            toolbarVoltar.txtTitulo.text = "Detalhes da Tarefa"
        }
    }

    private fun configMenu() {
        binding.toolbarVoltar.btnMenu.setOnClickListener { it ->
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.menu_notas, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_edit -> {
                        Intent(this@DetalhesNotaActivity, FormActivity::class.java).apply {
                            putExtra(CHAVE_NOTA_ID, nota.id)
                            startActivity(this)
                        }
                    }
                    R.id.menu_excluir -> {
                        dialogExcluirTarefa()
                    }
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }

    override fun onResume() {
        super.onResume()
        tarefas.clear()
        configDados()
        configRv()
    }

    private fun configDados() {
        with(binding) {
            tvTitulo.text = nota.titulo
            tvDesc.text = nota.descricao
        }
    }

    private suspend fun recuperaNota() {
        val notaId = intent.getStringExtra(CHAVE_NOTA_ID)

        notaId?.let { id ->
            notaDao.buscarPorId(id).collect {
                it?.let { notaRecuperada ->
                    nota = notaRecuperada
                }
            }
        }
    }

    private fun configRv() {
        with(binding) {
            rvTarefas.adapter = adapter
            rvTarefas.layoutManager = LinearLayoutManager(baseContext)

            nota.tarefas.forEachIndexed { index, tarefaStr ->
                val tarefa = Tarefa()
                tarefa.tarefa = tarefaStr
                tarefa.index = index
                tarefas.add(tarefa)
            }
            nota.tarefasBool.forEachIndexed { index, tarefaBool ->
                tarefas[index].checkBox = tarefaBool
            }

            adapter.atualiza(tarefas)

            adapter.onClick = {
                nota.tarefasBool[it.index] = it.checkBox
                if (!nota.tarefasBool.contains(false)) {
                    dialogFinalizarTarefa(it.index)
                } else {
                    CoroutineScope(Dispatchers.IO).launch { notaDao.salvar(nota) }
                }
            }
        }
    }

    private fun dialogFinalizarTarefa(index: Int) {
        DialogFinalizarBinding.inflate(layoutInflater).apply {
            val dialog = AlertDialog.Builder(this@DetalhesNotaActivity)
                .setView(root)
                .create()
            dialog.show()

            btnCancelar.setOnClickListener {
                nota.tarefasBool[index] = false
                tarefas[index].checkBox = false
                adapter.atualiza(tarefas)
                dialog.dismiss()
            }

            btnApagar.setOnClickListener {
                nota.finalizado = true
                CoroutineScope(Dispatchers.IO).launch {
                    notaDao.salvar(nota)
                }
                finish()
            }
        }
    }

    private fun dialogExcluirTarefa() {
        DialogExcluirBinding.inflate(layoutInflater).apply {
            val dialog = AlertDialog.Builder(this@DetalhesNotaActivity)
                .setView(root)
                .create()
            dialog.show()

            btnCancelar.setOnClickListener {
                dialog.dismiss()
            }

            btnApagar.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    notaDao.remove(nota)

                }
                dialog.dismiss()
                finish()
            }
        }
    }
}