package com.untels.redcetario.dialogos

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.untels.redcetario.R
import com.untels.redcetario.databinding.DialogoMensajeBinding

class DialogoMensaje(
    context: Context,
    private val titulo: String,
    private val descripcion: String
) : Dialog(context) {
    private lateinit var binding: DialogoMensajeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1) Inflamos el binding
        binding = DialogoMensajeBinding.inflate(layoutInflater)
        // 2) Le decimos al Dialog que use la root del binding
        setContentView(binding.root)

        // 3) Asignamos textos directamente al binding
        binding.tvTituloMensaje.text = titulo
        binding.tvContenidoMensaje.text = descripcion

        window?.setLayout(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawableResource(R.color.black_transparent)

        binding.btnAceptarMensaje.setOnClickListener {
            dismiss()
        }
    }
}