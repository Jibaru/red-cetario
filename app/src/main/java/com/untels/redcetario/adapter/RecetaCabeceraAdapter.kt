package com.untels.redcetario.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.untels.redcetario.R
import com.untels.redcetario.activities.RecetaActivity
import com.untels.redcetario.model.RecetaCabecera
import com.untels.redcetario.databinding.ItemRecetaBinding

class RecetaCabeceraAdapter constructor(
    var context: Context,
    var recetas: List<RecetaCabecera> = listOf()
) : RecyclerView.Adapter<RecetaCabeceraAdapter.RecetaCabeceraAdapterViewHolder>() {

    inner class RecetaCabeceraAdapterViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemRecetaBinding = ItemRecetaBinding.bind(itemView)

        fun bind(receta: RecetaCabecera) {
            binding.tvTitulo.text = receta.titulo
            binding.tvAutor.text = (receta.cliente.nombre + " " + receta.cliente.apePaterno)
            Picasso.get()
                .load(receta.urlImagen)
                .into(binding.imgReceta)
            val totalFavoritos = receta.totalFavoritos.toString() + " ❤"
            binding.tvFavoritos.text = totalFavoritos
            val tiempo = (receta.tiempoCoccion + receta.tiempoPrep).toString() + " minutos"
            binding.tvTiempo.text = tiempo

            binding.root.setOnClickListener {

                val bundle = Bundle().apply {
                    putInt("id_receta", receta.id)
                }

                val intent = Intent(context, RecetaActivity::class.java).apply {
                    putExtras(bundle)
                }

                context.startActivity(intent)
            }
        }
    }


    fun updateList(recetas: List<RecetaCabecera>) {
        this.recetas = recetas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecetaCabeceraAdapter.RecetaCabeceraAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_receta, parent, false)
        return RecetaCabeceraAdapterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecetaCabeceraAdapter.RecetaCabeceraAdapterViewHolder,
        position: Int
    ) {
        val receta: RecetaCabecera = recetas[position]
        holder.bind(receta)
    }

    override fun getItemCount(): Int {
        return recetas.size
    }

}

