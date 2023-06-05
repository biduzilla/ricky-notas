package com.example.rickynotas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.rickynotas.databinding.ItemTarefaBinding

class TarefasAdapter(
    tarefas: List<String> = emptyList(),
    var onClick: (tarefa: Int) -> Unit = {}
) : RecyclerView.Adapter<TarefasAdapter.ViewHolder>() {
    private val tarefas = tarefas.toMutableList()

    inner class ViewHolder(private val binding: ItemTarefaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var tarefa: String

        init {
            binding.btnRemover.setOnClickListener {
                onClick(tarefas.indexOf(tarefa))
            }
        }

        fun vincula(tarefa: String) {
            this.tarefa = tarefa

            binding.tarefaTxt.text = tarefa
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTarefaBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarefa = tarefas[position]
        holder.vincula(tarefa)
    }

    override fun getItemCount(): Int = tarefas.size

    fun atualiza(tarefas: List<String>) {
        this.tarefas.clear()
        this.tarefas.addAll(tarefas)
        notifyDataSetChanged()
    }
}