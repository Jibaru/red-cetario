package com.untels.redcetario.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.untels.redcetario.R
import com.untels.redcetario.databinding.ItemMaterialBinding
import com.untels.redcetario.model.Material
import com.untels.redcetario.utils.DialogoMensajeUtil

class MaterialAdapter constructor(
    var context: Context,
    var materiales: List<Material> = listOf()
) : RecyclerView.Adapter<MaterialAdapter.MaterialAdapterViewHolder>() {

    inner class MaterialAdapterViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemMaterialBinding = ItemMaterialBinding.bind(itemView)

        fun bind(material: Material) {
            binding.tvMaterial.text = material.nombre

            binding.root.setOnClickListener {
                DialogoMensajeUtil.showDialog(context, material.nombre, material.descripcion)
            }
        }
    }

    fun updateList(materiales: List<Material>) {
        this.materiales = materiales
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MaterialAdapter.MaterialAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_material, parent, false)
        return MaterialAdapterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MaterialAdapter.MaterialAdapterViewHolder,
        position: Int
    ) {
        val material: Material = materiales[position]
        holder.bind(material)
    }

    override fun getItemCount(): Int {
        return materiales.size
    }
}