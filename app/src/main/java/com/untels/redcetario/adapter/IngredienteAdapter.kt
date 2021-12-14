package com.untels.redcetario.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.untels.redcetario.R
import com.untels.redcetario.databinding.ItemIngredienteBinding
import com.untels.redcetario.model.Ingrediente
import com.untels.redcetario.utils.DialogoMensajeUtil

class IngredienteAdapter constructor(
    var context: Context,
    var ingredientes: List<Ingrediente> = listOf()
) : RecyclerView.Adapter<IngredienteAdapter.IngredienteAdapterViewHolder>() {

    inner class IngredienteAdapterViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemIngredienteBinding = ItemIngredienteBinding.bind(itemView)

        fun bind(ingrediente: Ingrediente) {
            val texto = ingrediente.pivot.cantidad.toString() + " " +
                    ingrediente.pivot.unidad + " " +
                    ingrediente.nombre
            binding.tvIngrediente.text = texto

            binding.root.setOnClickListener {
                DialogoMensajeUtil.showDialog(context, ingrediente.nombre, ingrediente.descripcion)
            }
        }
    }

    fun updateList(ingredientes: List<Ingrediente>) {
        this.ingredientes = ingredientes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredienteAdapter.IngredienteAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_ingrediente, parent, false)
        return IngredienteAdapterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: IngredienteAdapter.IngredienteAdapterViewHolder,
        position: Int
    ) {
        val ingrediente: Ingrediente = ingredientes[position]
        holder.bind(ingrediente)
    }

    override fun getItemCount(): Int {
        return ingredientes.size
    }
}