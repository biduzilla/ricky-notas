package com.example.rickynotas.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickynotas.R
import com.example.rickynotas.data.AppDatabase
import com.example.rickynotas.data.dao.NotaDao
import com.example.rickynotas.databinding.ActivityMainBinding
import com.example.rickynotas.extension.Toast
import com.example.rickynotas.extension.iniciaActivity
import com.example.rickynotas.model.Nota
import com.example.rickynotas.ui.adapter.NotasAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val notaDao: NotaDao by lazy {
        val db = AppDatabase.instancia(this)
        db.notaDao()
    }
    private val adapter by lazy {
        NotasAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configClicks()
        configRv()
        lifecycleScope.launch { buscaNotas() }

    }

    private fun ocultarTeclado() {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.btnAdd.windowToken, 0)
    }

    private suspend fun buscaNotas() {
        notaDao.buscaTodos().collect { notas ->
            adapter.atualiza(notas)
        }
    }

    private fun configClicks() {
        binding.btnAdd.setOnClickListener {
            iniciaActivity(FormActivity::class.java)
        }
    }

    private fun configRv() {
        with(binding) {
            rvNotas.adapter = adapter
            rvNotas.layoutManager = LinearLayoutManager(baseContext)

            adapter.onClick = {
                Toast(baseContext, it.titulo)
            }
        }
    }
}