package com.untels.redcetario.dialogos

import android.app.Dialog
import android.app.Notification
import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.untels.redcetario.R
import com.untels.redcetario.databinding.DialogoNotificacionBinding
import com.untels.redcetario.model.Notificacion

class DialogoNotificacion(context: Context, private val notificacion: Notificacion) : Dialog(context)  {
    private lateinit var binding: DialogoNotificacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialogo_notificacion)
        val titulo: TextView = findViewById(R.id.tvTituloNoti)
        val descripcion: TextView = findViewById(R.id.tvDescripcionNoti)
        val fechaEnvio: TextView = findViewById(R.id.tvFechaEnvio)
        titulo.text = notificacion.titulo
        descripcion.text = notificacion.descripcion
        fechaEnvio.text = notificacion.fechaEnvio
        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        window?.setBackgroundDrawableResource(R.color.black_transparent)

        binding.btnAceptarNoti.setOnClickListener {
            dismiss()
        }
    }
}