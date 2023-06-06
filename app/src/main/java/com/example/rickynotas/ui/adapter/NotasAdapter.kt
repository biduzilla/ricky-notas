package com.example.rickynotas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickynotas.databinding.ItemNotaBinding
import com.example.rickynotas.model.Nota

class NotasAdapter(
    notas: List<Nota> = emptyList(),
    var onClick: (nota: Nota) -> Unit = {}
) : RecyclerView.Adapter<NotasAdapter.ViewHolder>() {
    private val notas = notas.toMutableList()

    inner class ViewHolder(private val binding: ItemNotaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var nota: Nota

        init {
            itemView.setOnClickListener {
                if (::nota.isInitialized) {
                    onClick(nota)
                }
            }
        }

        fun vincula(nota: Nota) {
            this.nota = nota

            with(binding) {
                notaTxt.text = nota.titulo
                dataTxt.text = nota.data
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotaBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nota = notas[position]
        holder.vincula(nota)
    }

    override fun getItemCount(): Int = notas.size

    fun atualiza(notas: List<Nota>) {
        this.notas.clear()
        this.notas.addAll(notas)
        this.notas.reverse()
        notifyDataSetChanged()
    }
}