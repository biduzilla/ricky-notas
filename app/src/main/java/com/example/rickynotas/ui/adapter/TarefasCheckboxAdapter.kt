package com.example.rickynotas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickynotas.databinding.ItemTarefaCheckboxBinding
import com.example.rickynotas.model.Tarefa

class TarefasCheckboxAdapter(
    tarefas: List<Tarefa> = emptyList(),
    var onClick: (tarefa: Tarefa) -> Unit = {}
) : RecyclerView.Adapter<TarefasCheckboxAdapter.ViewHolder>() {
    private val tarefas = tarefas.toMutableList()

    inner class ViewHolder(private val binding: ItemTarefaCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var tarefa: Tarefa

        init {
            binding.checkbox.setOnClickListener {
                tarefa.checkBox = binding.checkbox.isChecked
                onClick(tarefa)
            }
        }

        fun vincula(tarefa: Tarefa) {
            this.tarefa = tarefa

            with(binding) {
                checkbox.text = tarefa.tarefa
                checkbox.isChecked = tarefa.checkBox
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTarefaCheckboxBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarefa = tarefas[position]
        holder.vincula(tarefa)
    }

    override fun getItemCount(): Int = tarefas.size

    fun atualiza(tarefas: List<Tarefa>) {
        this.tarefas.clear()
        this.tarefas.addAll(tarefas)
        notifyDataSetChanged()
    }
}