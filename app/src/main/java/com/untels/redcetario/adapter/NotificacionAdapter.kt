package com.untels.redcetario.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.untels.redcetario.R
import com.untels.redcetario.databinding.ItemNotificacionBinding
import com.untels.redcetario.model.Notificacion

class NotificacionAdapter constructor(
    var context: Context,
    var notificaciones: List<Notificacion> = listOf()
) : RecyclerView.Adapter<NotificacionAdapter.NotificacionAdapterViewHolder>() {

    inner class NotificacionAdapterViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemNotificacionBinding = ItemNotificacionBinding.bind(itemView)

        fun bind(notificacion: Notificacion) {
            // TODO: Mostrar notificación
        }
    }

    fun updateList(notificaciones: List<Notificacion>) {
        this.notificaciones = notificaciones
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificacionAdapter.NotificacionAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_notificacion, parent, false)
        return NotificacionAdapterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NotificacionAdapter.NotificacionAdapterViewHolder,
        position: Int
    ) {
        val notificacion: Notificacion = notificaciones[position]
        holder.bind(notificacion)
    }

    override fun getItemCount(): Int {
        return notificaciones.size
    }
}