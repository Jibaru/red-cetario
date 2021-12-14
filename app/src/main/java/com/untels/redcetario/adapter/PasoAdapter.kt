package com.untels.redcetario.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.untels.redcetario.R
import com.untels.redcetario.databinding.ItemPasoBinding
import com.untels.redcetario.model.Paso

class PasoAdapter constructor(
    var context: Context,
    var pasos: List<Paso> = listOf()
) : RecyclerView.Adapter<PasoAdapter.PasoAdapterViewHolder>() {

    inner class PasoAdapterViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemPasoBinding = ItemPasoBinding.bind(itemView)

        fun bind(paso: Paso) {
            val texto = paso.numeroOrden.toString() + ".- " + paso.contenido
            binding.tvContenido.text = texto
        }
    }

    fun updateList(pasos: List<Paso>) {
        this.pasos = pasos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PasoAdapter.PasoAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_paso, parent, false)
        return PasoAdapterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PasoAdapter.PasoAdapterViewHolder,
        position: Int
    ) {
        val paso: Paso = pasos[position]
        holder.bind(paso)
    }

    override fun getItemCount(): Int {
        return pasos.size
    }
}