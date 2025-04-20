package com.untels.redcetario.dialogos

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.untels.redcetario.R
import com.untels.redcetario.databinding.DialogoNotificacionBinding
import com.untels.redcetario.model.Notificacion

class DialogoNotificacion(context: Context, private val notificacion: Notificacion) : Dialog(context)  {
    private lateinit var binding: DialogoNotificacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogoNotificacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTituloNoti.text      = notificacion.titulo
        binding.tvDescripcionNoti.text = notificacion.descripcion
        binding.tvFechaEnvio.text      = notificacion.fechaEnvio

        binding.btnAceptarNoti.setOnClickListener { dismiss() }
    }

    override fun onStart() {
        super.onStart()
        window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawableResource(R.color.black_transparent)
    }
}