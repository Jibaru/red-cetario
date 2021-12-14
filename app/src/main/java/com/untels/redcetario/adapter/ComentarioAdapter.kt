package com.untels.redcetario.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.untels.redcetario.R
import com.untels.redcetario.databinding.ItemComentarioBinding
import com.untels.redcetario.model.Comentario

class ComentarioAdapter constructor(
    var context: Context,
    var comentarios: List<Comentario> = listOf()
) : RecyclerView.Adapter<ComentarioAdapter.ComentarioAdapterViewHolder>() {

    inner class ComentarioAdapterViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemComentarioBinding = ItemComentarioBinding.bind(itemView)

        fun bind(comentario: Comentario) {
            binding.tvFechaComentario.text = comentario.createdAt.split("T")[0]
            binding.tvComentario.text = comentario.descripcion
        }
    }

    fun updateList(comentarios: List<Comentario>) {
        this.comentarios = comentarios
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComentarioAdapter.ComentarioAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_comentario, parent, false)
        return ComentarioAdapterViewHolder (view)
    }

    override fun onBindViewHolder(
        holder: ComentarioAdapter.ComentarioAdapterViewHolder,
        position: Int
    ) {
        val comentario: Comentario = comentarios[position]
        holder.bind(comentario)
    }

    override fun getItemCount(): Int {
        return comentarios.size
    }
}