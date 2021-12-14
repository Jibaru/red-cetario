package com.untels.redcetario.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.untels.redcetario.R
import com.untels.redcetario.databinding.ItemNotificacionBinding
import com.untels.redcetario.model.Notificacion
import com.untels.redcetario.service.ServiceManager
import com.untels.redcetario.utils.CargadorUtil
import com.untels.redcetario.utils.DialogoNotificacionUtil

class NotificacionAdapter constructor(
    var context: Context,
    var notificaciones: List<Notificacion> = listOf()
) : RecyclerView.Adapter<NotificacionAdapter.NotificacionAdapterViewHolder>() {

    inner class NotificacionAdapterViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemNotificacionBinding = ItemNotificacionBinding.bind(itemView)

        fun bind(notificacion: Notificacion) {
            binding.tvTituloNotificacion.text = notificacion.titulo
            binding.tvFechaNotificacion.text = notificacion.fechaEnvio
            val visto = notificacion.fechaVisto != null
            if (visto) {
                binding.iconoNuevo.isVisible = false
            }

            binding.root.setOnClickListener {
                if (!visto) {
                    binding.iconoNuevo.isVisible = false
                    Thread {
                        ServiceManager.getNotificacionService().actualizarFechaVisto(notificacion.id)
                    }.start()
                }
                DialogoNotificacionUtil.showDialog(context, notificacion)
            }
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